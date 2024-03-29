package com.heroes.hero.repository;

import com.heroes.hero.model.Hero;
import com.heroes.hero.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Integer> {

}
