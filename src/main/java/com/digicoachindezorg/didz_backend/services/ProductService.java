package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.ProductDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProduct(Long id) throws RecordNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + id));
        return toProductDto(product);
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = fromProductDto(productDto);
        Product createdProduct = productRepository.save(product);
        return toProductDto(createdProduct);
    }

    public ProductDto updateProduct(Long id, ProductDto productDtoToUpdate) throws RecordNotFoundException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Product not found with id: " + id));

        // Update the fields of the existing product
        BeanUtils.copyProperties(productDtoToUpdate, existingProduct);

        Product updatedProduct = productRepository.save(existingProduct);
        return toProductDto(updatedProduct);
    }

    public void deleteProduct(Long id) throws RecordNotFoundException {
        if (!productRepository.existsById(id)) {
            throw new RecordNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    private ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        // Additional mapping for nested entities
        return productDto;
    }

    private Product fromProductDto(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        // Additional mapping for nested entities
        return product;
    }
}