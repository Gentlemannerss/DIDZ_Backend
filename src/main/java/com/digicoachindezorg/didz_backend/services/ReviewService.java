package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.ReviewInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ReviewOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.BadRequestException;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.Review;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.ProductRepository;
import com.digicoachindezorg.didz_backend.repositories.ReviewRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    public ReviewOutputDto getReview(Long id) throws RecordNotFoundException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Review not found with id: " + id));
        return transferReviewToReviewOutputDto(review);
    }

    public ReviewOutputDto createReview(ReviewInputDto reviewInputDto) throws RecordNotFoundException {
        User user = userRepository.findById(reviewInputDto.getCustomerId()).orElseThrow(() -> new RecordNotFoundException("User not found"));
        List <Review> reviews = reviewRepository.findByCustomer(user);

        for (Review review : reviews) {
            if (review.getProduct().getProductId().equals(reviewInputDto.getProductId())) {
                throw new BadRequestException("Review already exists for this product and user");
            }
        }

        Review review = transferReviewInputDtoToReview(reviewInputDto);
        Review createdReview = reviewRepository.save(review);
        return transferReviewToReviewOutputDto(createdReview);
    }

    public ReviewOutputDto updateReview(Long id, ReviewInputDto reviewInputDtoToUpdate) throws RecordNotFoundException {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Review not found with id: " + id));

        // Update the fields of the existing review
        Review updatedReview = updateReviewInputDtoToReview(reviewInputDtoToUpdate, existingReview);

        Review savedReview = reviewRepository.save(updatedReview);
        return transferReviewToReviewOutputDto(savedReview);
    }

    public void deleteReview(Long id) throws RecordNotFoundException {
        if (!reviewRepository.existsById(id)) {
            throw new RecordNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }

    public List<ReviewOutputDto> getAllReviewsPerProduct(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProductProductId(productId);
        return reviews.stream()
                .map(this::transferReviewToReviewOutputDto)
                .collect(Collectors.toList());
    }

    private ReviewOutputDto transferReviewToReviewOutputDto(Review review) {
        ReviewOutputDto reviewOutputDto = new ReviewOutputDto();
        reviewOutputDto.setCustomer(review.getCustomer());
        reviewOutputDto.setReviewDescription(review.getReviewDescription());
        reviewOutputDto.setReviewId(review.getReviewId());
        reviewOutputDto.setProduct(review.getProduct());
        reviewOutputDto.setScore(review.getScore());
        reviewOutputDto.setDateOfWriting(review.getDateOfWriting());

        reviewOutputDto.setCustomer((review.getCustomer()));

        return reviewOutputDto;
    }

    private Review transferReviewInputDtoToReview(ReviewInputDto reviewInputDto) {
        Review review = new Review();
        if (reviewInputDto.getCustomerId()!=null) {
            User customer = userRepository.findById(reviewInputDto.getCustomerId())
                    .orElseThrow(() -> new RecordNotFoundException("User is not found with id: " + reviewInputDto.getCustomerId()));
            review.setCustomer(customer);
        }
        if (reviewInputDto.getReviewDescription()!=null) {
            review.setReviewDescription(reviewInputDto.getReviewDescription());
        }
        if (reviewInputDto.getProductId()!=null) {
            Product product = productRepository.findById(reviewInputDto.getProductId())
                    .orElseThrow(() -> new RecordNotFoundException("Product is not found with id: " + reviewInputDto.getProductId()));
            review.setProduct(product);
        }
        if (reviewInputDto.getScore()!=null) {
            review.setScore(reviewInputDto.getScore());
        }

        review.setDateOfWriting(LocalDate.now());

        return review;
    }

    private Review updateReviewInputDtoToReview(ReviewInputDto reviewInputDto, Review review) {
        if (reviewInputDto.getCustomerId()!=null) {
            User customer = userRepository.findById(reviewInputDto.getCustomerId())
                    .orElseThrow(() -> new RecordNotFoundException("User is not found with id: " + reviewInputDto.getCustomerId()));
            review.setCustomer(customer);
        }
        if (reviewInputDto.getReviewDescription()!=null) {
            review.setReviewDescription(reviewInputDto.getReviewDescription());
        }
        if (reviewInputDto.getProductId()!=null) {
            Product product = productRepository.findById(reviewInputDto.getProductId())
                    .orElseThrow(() -> new RecordNotFoundException("Product is not found with id: " + reviewInputDto.getProductId()));
            review.setProduct(product);
        }
        if (reviewInputDto.getScore()!=null) {
            review.setScore(reviewInputDto.getScore());
        }
        return review;
    }
}