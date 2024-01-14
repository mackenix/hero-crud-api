package com.heroes.hero.service;

import com.heroes.hero.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(int heroId, ReviewDto reviewDto);

    List<ReviewDto> getReviewsByHeroId(int heroId);
}
