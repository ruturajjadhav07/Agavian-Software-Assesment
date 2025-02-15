package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Review;
import com.example.service.ReviewService;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Accepts a review and associates it with a given movie.
    @PostMapping("/movies/{movieid}/reviews")
    public Review postReview(@PathVariable long movieid, @RequestParam String reviewerName,
            @RequestParam String comment,
            @RequestParam int rating) {

        return reviewService.addReview(movieid, reviewerName, comment, rating);
    }

    @PutMapping("/reviews/{reviewId}")
    public Review updatReview(@PathVariable long reviewId,
            @RequestParam String comment,
            @RequestParam int rating) {
        return reviewService.updateReview(reviewId, comment, rating);

    }
}
