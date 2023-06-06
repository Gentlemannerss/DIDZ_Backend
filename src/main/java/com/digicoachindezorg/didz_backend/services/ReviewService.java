package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.ReviewInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ReviewOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.Review;
import com.digicoachindezorg.didz_backend.models.User;
import com.digicoachindezorg.didz_backend.repositories.ProductRepository;
import com.digicoachindezorg.didz_backend.repositories.ReviewRepository;
import com.digicoachindezorg.didz_backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

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

    public List<ReviewOutputDto> getAllReviewsPerProduct(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProductProductId(productId);
        return reviews.stream()
                .map(this::transferReviewToReviewOutputDto)
                .collect(Collectors.toList());
    }

    public ReviewOutputDto getReview(Long id) throws RecordNotFoundException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Review not found with id: " + id));
        return transferReviewToReviewOutputDto(review);
    }

    public ReviewOutputDto createReview(ReviewInputDto reviewInputDto) throws RecordNotFoundException {
        Review review = transferReviewInputDtoToReview(reviewInputDto);
        User customer = userRepository.findById(reviewInputDto.getCustomer().getId())
                .orElseThrow(() -> new RecordNotFoundException("User not found with id: " + reviewInputDto.getCustomer().getId()));
        review.setCustomer(customer);
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

    public ReviewOutputDto createReviewForProduct(ReviewInputDto reviewInputDto, Long productId) throws RecordNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + productId));

        Review review = transferReviewInputDtoToReview(reviewInputDto);
        review.setProduct(product);

        Review createdReview = reviewRepository.save(review);
        return transferReviewToReviewOutputDto(createdReview);
    }

    private ReviewOutputDto transferReviewToReviewOutputDto(Review review) {
        ReviewOutputDto reviewOutputDto = new ReviewOutputDto();
        reviewOutputDto.setCustomer(review.getCustomer());
        reviewOutputDto.setReviewDescription(review.getReviewDescription());
        reviewOutputDto.setReviewId(review.getReviewId());
        reviewOutputDto.setProduct(review.getProduct());
        reviewOutputDto.setScore(review.getScore());
        reviewOutputDto.setDateOfWriting(review.getDateOfWriting());

        reviewOutputDto.setCustomer((review.getCustomer())); //Dit geeft een foutmelding. Waar komt dit vandaan.

        return reviewOutputDto;
    }

    private Review transferReviewInputDtoToReview(ReviewInputDto reviewInputDto) {
        Review review = new Review();
        if (reviewInputDto.getCustomer()!=null) {
            review.setCustomer(reviewInputDto.getCustomer());
        }
        if (reviewInputDto.getReviewDescription()!=null) {
            review.setReviewDescription(reviewInputDto.getReviewDescription());
        }
        if (reviewInputDto.getProduct()!=null) {
            review.setProduct(reviewInputDto.getProduct());
        }
        if (reviewInputDto.getScore()!=null) {
            review.setScore(reviewInputDto.getScore());
        }
        if (reviewInputDto.getDateOfWriting()!=null) {
            review.setDateOfWriting(reviewInputDto.getDateOfWriting());
        }
        if (reviewInputDto.getCustomer()!=null) {
            review.setCustomer((reviewInputDto.getCustomer())); //Ook hier dezelfde fout
        }
        return review;
    }

    private Review updateReviewInputDtoToReview(ReviewInputDto reviewInputDto, Review review) {
        if (reviewInputDto.getCustomer()!=null) {
            review.setCustomer(reviewInputDto.getCustomer());
        }
        if (reviewInputDto.getReviewDescription()!=null) {
            review.setReviewDescription(reviewInputDto.getReviewDescription());
        }
        if (reviewInputDto.getProduct()!=null) {
            review.setProduct(reviewInputDto.getProduct());
        }
        if (reviewInputDto.getScore()!=null) {
            review.setScore(reviewInputDto.getScore());
        }
        if (reviewInputDto.getDateOfWriting()!=null) {
            review.setDateOfWriting(reviewInputDto.getDateOfWriting());
        }
        if (reviewInputDto.getCustomer()!=null) {
            review.setCustomer((reviewInputDto.getCustomer())); //Wat wordt er terug gestuurd aan informatie.
        }
        return review;
    }
}