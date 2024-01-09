package com.heroes.hero.service;

import com.heroes.hero.dto.HeroDto;

import java.util.List;

public interface HeroService {

    HeroDto createHero(HeroDto heroDto);
    List<HeroDto> getAllHeroes();

    HeroDto getHeroById(int id);

    HeroDto updateHero(HeroDto heroDto, int id);

    void deleteHero(int id);
}
