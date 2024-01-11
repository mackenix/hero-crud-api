package com.heroes.hero.service;

import com.heroes.hero.dto.HeroDto;
import com.heroes.hero.dto.HeroResponse;

import java.util.List;

public interface HeroService {

    HeroDto createHero(HeroDto heroDto);
    HeroResponse getAllHeroes(int pageNo, int pageSize);
    HeroDto getHeroById(int id);
    HeroDto updateHero(HeroDto heroDto, int id);
    void deleteHero(int id);
}
