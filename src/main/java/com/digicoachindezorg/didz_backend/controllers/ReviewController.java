package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.ReviewDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsPerProduct(@PathVariable Long productId) {
        List<ReviewDto> reviews = reviewService.getAllReviewsPerProduct(productId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long id) throws RecordNotFoundException {
        ReviewDto reviewDto = reviewService.getReview(id);
        return ResponseEntity.ok(reviewDto);
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) throws RecordNotFoundException {
        ReviewDto createdReview = reviewService.createReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) throws RecordNotFoundException {
        ReviewDto updatedReview = reviewService.updateReview(id, reviewDto);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) throws RecordNotFoundException {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/product/{productId}")
    public ResponseEntity<ReviewDto> createReviewForProduct(@RequestBody ReviewDto reviewDto, @PathVariable Long productId) throws RecordNotFoundException {
        ReviewDto createdReview = reviewService.createReviewForProduct(reviewDto, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }
}