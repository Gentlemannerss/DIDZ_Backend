package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.ReviewDto;
import com.digicoachindezorg.didz_backend.dtos.UserDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.Review;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.ProductRepository;
import com.digicoachindezorg.didz_backend.repositories.ReviewRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository,ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<ReviewDto> getAllReviewsPerProduct(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProductProductId(productId);
        return reviews.stream()
                .map(this::toReviewDto)
                .collect(Collectors.toList());
    }

    public ReviewDto getReview(Long id) throws RecordNotFoundException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Review not found with id: " + id));
        return toReviewDto(review);
    }

    public ReviewDto createReview(ReviewDto reviewDto) throws RecordNotFoundException {
        Review review = fromReviewDto(reviewDto);
        User customer = userRepository.findById(reviewDto.getCustomer().getId())
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + reviewDto.getCustomer().getId()));
        review.setCustomer(customer);
        Review createdReview = reviewRepository.save(review);
        return toReviewDto(createdReview);
    }

    public ReviewDto updateReview(Long id, ReviewDto reviewDtoToUpdate) throws RecordNotFoundException {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Review not found with id: " + id));

        // Update the fields of the existing review
        BeanUtils.copyProperties(reviewDtoToUpdate, existingReview);

        Review updatedReview = reviewRepository.save(existingReview);
        return toReviewDto(updatedReview);
    }

    public void deleteReview(Long id) throws RecordNotFoundException {
        if (!reviewRepository.existsById(id)) {
            throw new RecordNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }

    public ReviewDto createReviewForProduct(ReviewDto reviewDto, Long productId) throws RecordNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + productId));

        Review review = fromReviewDto(reviewDto);
        review.setProduct(product);

        Review createdReview = reviewRepository.save(review);
        return toReviewDto(createdReview);
    }

    private ReviewDto toReviewDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        BeanUtils.copyProperties(review, reviewDto);
        reviewDto.setCustomer(toUserDto(review.getCustomer()));
        return reviewDto;
    }

    private Review fromReviewDto(ReviewDto reviewDto) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDto, review);
        return review;
    }

    private UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}
