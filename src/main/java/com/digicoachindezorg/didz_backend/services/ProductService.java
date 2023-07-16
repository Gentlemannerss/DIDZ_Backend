package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.ProductInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ProductOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.Review;
import com.digicoachindezorg.didz_backend.models.StudyGroup;
import com.digicoachindezorg.didz_backend.repositories.InvoiceRepository;
import com.digicoachindezorg.didz_backend.repositories.ProductRepository;
import com.digicoachindezorg.didz_backend.repositories.ReviewRepository;
import com.digicoachindezorg.didz_backend.repositories.StudyGroupRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Lazy
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final InvoiceRepository invoiceRepository;
    private final StudyGroupRepository studyGroupRepository;

    public ProductService(ProductRepository productRepository, ReviewRepository reviewRepository, InvoiceRepository invoiceRepository, StudyGroupRepository studyGroupRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.invoiceRepository = invoiceRepository;
        this.studyGroupRepository = studyGroupRepository;
    }

    public List<ProductOutputDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductOutputDto> list = new ArrayList<>();
        for (Product product : products) {
            list.add(transferProductToProductOutputDto(product));
        }
        return list;
    }

    /* todo: implement this method
    public List<ProductOutputDto> getProductsByUserId(Long userId) {
        invoiceRepository.findByUserUserId(userId);
        List<Product> products = productRepository.findByUserId(userId);
        return products.stream()
                .map(this::transferProductToProductOutputDto)
                .collect(Collectors.toList());
    }*/

    public ProductOutputDto getProduct(Long id) throws RecordNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + id));
        return transferProductToProductOutputDto(product);
    }

    public ProductOutputDto createProduct(ProductInputDto productDto) {
        Product product = transferProductInputDtoToProduct(productDto);
        Product createdProduct = productRepository.save(product);
        return transferProductToProductOutputDto(createdProduct);
    }

    public ProductOutputDto updateProduct(Long id, ProductInputDto productDtoToUpdate) throws RecordNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + id));
        Product updatedProduct = updateProductInputDtoToProduct(productDtoToUpdate, existingProduct);
        Product savedProduct = productRepository.save(updatedProduct);
        return transferProductToProductOutputDto(savedProduct);
    }

    public void deleteProduct(Long id) throws RecordNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new RecordNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductOutputDto transferProductToProductOutputDto(Product product) {
        ProductOutputDto productDto = new ProductOutputDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setReviews(product.getReviews());
        productDto.setPrice(product.getPrice());
        productDto.setProductType(product.getProductType());
        productDto.setStudyGroup(product.getStudyGroup());
        return productDto;
    }

    private Product transferProductInputDtoToProduct(ProductInputDto productDto) {
        Product product = new Product();
        if (productDto.getProductName()!=null) {
            product.setProductName(productDto.getProductName());
        }
        if (productDto.getReviewIds()!=null) {
            List <Review> reviews = reviewRepository.findAllById(productDto.getReviewIds());
            product.setReviews(reviews);
        }
        if (productDto.getPrice()!=null) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getProductType()!=null) {
            product.setProductType(productDto.getProductType());
        }
        if (productDto.getStudyGroupId()!=null) {
            StudyGroup studyGroup = studyGroupRepository.findById(productDto.getStudyGroupId())
                    .orElseThrow(() -> new RecordNotFoundException("StudyGroup not found with id: " + productDto.getStudyGroupId()));
            product.setStudyGroup(studyGroup);
        }
        return product;
    }

    private Product updateProductInputDtoToProduct(ProductInputDto productDto, Product product) {
        if (productDto.getProductName()!=null) {
            product.setProductName(productDto.getProductName());
        }
        if (productDto.getReviewIds()!=null) {
            List <Review> reviews = reviewRepository.findAllById(productDto.getReviewIds());
            product.setReviews(reviews);
        }
        if (productDto.getPrice()!=null) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getProductType()!=null) {
            product.setProductType(productDto.getProductType());
        }
        if (productDto.getStudyGroupId()!=null) {
            StudyGroup studyGroup = studyGroupRepository.findById(productDto.getStudyGroupId())
                    .orElseThrow(() -> new RecordNotFoundException("StudyGroup not found with id: " + productDto.getStudyGroupId()));
            product.setStudyGroup(studyGroup);
        }
        return product;
    }
}