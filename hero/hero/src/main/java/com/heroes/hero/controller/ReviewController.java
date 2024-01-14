package com.heroes.hero.controller;

import com.heroes.hero.dto.ReviewDto;
import com.heroes.hero.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/hero/")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController (ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping("/{heroId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "heroId") int heroId, @RequestBody ReviewDto reviewDto){

        return new ResponseEntity<>(reviewService.createReview(heroId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/{heroId}/reviews")
    public List<ReviewDto> getReviewByHeroId(@PathVariable (value = "heroId") int heroId){

        return reviewService.getReviewsByHeroId(heroId);
    }
}
