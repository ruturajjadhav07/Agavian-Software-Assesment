package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Movie;
import com.example.entity.Review;
import com.example.repository.MovieRepository;
import com.example.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewService(MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }

    // Accepts a review and associates it with a given movie.
    public Review addReview(long movieId, String reviewerName, String comment, int rating) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id " + movieId));

        if (reviewerName == null || reviewerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Reviewer name cannot be empty");
        }

        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        Review review = new Review();
        review.setReviewerName(reviewerName);
        review.setComment(comment);
        review.setRating(rating);
        review.setMovie(movie);

        return reviewRepository.save(review);
    }

    // Updates the review comment and rating.
    public Review updateReview(long reviewId, String comment, int rating) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with id " + reviewId));

        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        review.setComment(comment);
        review.setRating(rating);

        return reviewRepository.save(review);
    }
}
