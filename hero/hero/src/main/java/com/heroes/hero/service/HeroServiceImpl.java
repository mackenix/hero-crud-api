package com.heroes.hero.service;

import com.heroes.hero.dto.HeroDto;
import com.heroes.hero.dto.HeroResponse;
import com.heroes.hero.exceptions.HeroNotFoundException;
import com.heroes.hero.model.Hero;
import com.heroes.hero.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService{

    private final HeroRepository repository;

    @Autowired
    public HeroServiceImpl(HeroRepository repository) {
        Assert.notNull(repository, "HeroRepository must not be null!");
        this.repository = repository;
    }

    @Override
    public HeroDto createHero(HeroDto heroDto) {
        Hero hero = new Hero();
        hero.setName(heroDto.getName());
        hero.setPower(heroDto.getPower());

        Hero savedHero = repository.save(hero);

        HeroDto response = new HeroDto();
        response.setId(savedHero.getId());
        response.setName(savedHero.getName());
        response.setPower(savedHero.getPower());

        return response;
    }

    @Override
    public HeroResponse getAllHeroes(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Hero> heroes = repository.findAll(pageable);
        List<Hero> listOfHeroes = heroes.getContent();
        //map will return a new list
        List<HeroDto> content = listOfHeroes.stream().map(this::mapToDto).collect(Collectors.toList());

        HeroResponse response = new HeroResponse();
        response.setContent(content);
        response.setPageNo(heroes.getNumber());
        response.setPageSize(heroes.getSize());
        response.setTotalElements(heroes.getTotalElements());
        response.setTotalPages(heroes.getTotalPages());
        response.setLast(heroes.isLast());

        return response;
    }

    @Override
    public HeroDto getHeroById(int id) {
        Hero hero = repository.findById(id).orElseThrow(() -> new HeroNotFoundException("Hero could not be found"));

        return mapToDto(hero);
    }

    @Override
    public HeroDto updateHero(HeroDto heroDto, int id) {

        Hero hero = repository.findById(id).orElseThrow(() -> new HeroNotFoundException("Hero could not be updated"));

        hero.setName(heroDto.getName());
        hero.setPower(heroDto.getPower());

        Hero updatedHero = repository.save(hero);
        return mapToDto(updatedHero);
    }

    @Override
    public void deleteHero(int id) {
        Hero hero = repository.findById(id).orElseThrow(() -> new HeroNotFoundException("Hero could not be found for deletion"));

        repository.delete(hero);
    }

    private HeroDto mapToDto(Hero hero){
        HeroDto heroDto = new HeroDto();
        heroDto.setId(hero.getId());
        heroDto.setName(hero.getName());
        heroDto.setPower(hero.getPower());

        return heroDto;
    }

    private Hero mapToEntity(HeroDto heroDto){
        Hero hero = new Hero();
        hero.setId(heroDto.getId());
        hero.setName(heroDto.getName());
        hero.setPower(heroDto.getPower());

        return hero;
    }
}
