package com.heroes.hero.repository;

import com.heroes.hero.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository  extends JpaRepository<Review, Integer> {

    List<Review> findByHeroId(int heroId);

}
