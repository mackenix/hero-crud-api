package com.heroes.hero.service;

import com.heroes.hero.dto.ReviewDto;
import com.heroes.hero.exceptions.HeroNotFoundException;
import com.heroes.hero.model.Hero;
import com.heroes.hero.model.Review;
import com.heroes.hero.repository.HeroRepository;
import com.heroes.hero.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    HeroRepository heroRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, HeroRepository heroRepository){
        this.reviewRepository = reviewRepository;
        this.heroRepository = heroRepository;
    }

    @Override
    public ReviewDto createReview(int heroId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);

        Hero hero = heroRepository.findById(heroId).orElseThrow(() -> new HeroNotFoundException("Hero with associated Review not found!"));

        review.setHero(hero);

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByHeroId(int heroId) {

        List<Review> reviews = reviewRepository.findByHeroId(heroId);
        return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }
    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
