package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.ReviewInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ReviewOutputDto;
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
    @PostMapping
    public ResponseEntity<ReviewOutputDto> createReview(@RequestBody ReviewInputDto reviewInputDto) throws RecordNotFoundException { /*@PathVaribale OF @RequestBody voor de userID*/
        ReviewOutputDto reviewDto = reviewService.createReview(reviewInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReviewOutputDto> updateReview(@PathVariable Long id, @RequestBody ReviewInputDto reviewInputDto) throws RecordNotFoundException {
        ReviewOutputDto updatedReview = reviewService.updateReview(id, reviewInputDto);
        return ResponseEntity.ok(updatedReview);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) throws RecordNotFoundException {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewOutputDto> getReview(@PathVariable Long id) throws RecordNotFoundException {
        ReviewOutputDto reviewDto = reviewService.getReview(id);
        return ResponseEntity.ok(reviewDto);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewOutputDto>> getAllReviewsPerProduct(@PathVariable Long productId) {
        List<ReviewOutputDto> reviews = reviewService.getAllReviewsPerProduct(productId);
        return ResponseEntity.ok(reviews);
    }
}