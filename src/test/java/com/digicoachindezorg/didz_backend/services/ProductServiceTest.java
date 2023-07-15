package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.ProductInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ProductOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.models.ProductType;
import com.digicoachindezorg.didz_backend.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void getAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setProductId(1L);
        Product product2 = new Product();
        product2.setProductId(2L);
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<ProductOutputDto> result = productService.getAllProducts();

        // Assert
        assertEquals(products.size(), result.size());
        assertEquals(product1.getProductId(), result.get(0).getProductId());
        assertEquals(product2.getProductId(), result.get(1).getProductId());
    }

    @Test
    void getProduct() {
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("testproduct");
        product.setProductType(ProductType.BOOK);
        product.setPrice(10.00);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productRepository.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(product.getProductId(), result.get().getProductId());
        assertEquals(product.getProductName(), result.get().getProductName());
        assertEquals(product.getProductType(), result.get().getProductType());
        assertEquals(product.getPrice(), result.get().getPrice());
    }

    @Test
    void createProduct() {
        // Arrange
        ProductInputDto productInputDto = new ProductInputDto();
        productInputDto.setProductName("testproduct");
        productInputDto.setProductType(ProductType.BOOK);
        productInputDto.setPrice(10.00);

        Product savedProduct = new Product();
        savedProduct.setProductId(1L);
        savedProduct.setProductName(productInputDto.getProductName());
        savedProduct.setProductType(productInputDto.getProductType());
        savedProduct.setPrice(productInputDto.getPrice());

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        ProductOutputDto productOutputDto = productService.createProduct(productInputDto);

        // Assert
        assertEquals(savedProduct.getProductId(), productOutputDto.getProductId());
        assertEquals(savedProduct.getProductName(), productOutputDto.getProductName());
        assertEquals(savedProduct.getProductType(), productOutputDto.getProductType());
        assertEquals(savedProduct.getPrice(), productOutputDto.getPrice());
    }

    @Test
    void updateProduct() {
        // Arrange
        ProductInputDto updatedProductDto = new ProductInputDto();
        updatedProductDto.setProductName("updatedproduct");
        updatedProductDto.setProductType(ProductType.BOOK);
        updatedProductDto.setPrice(20.00);

        Product existingProduct = new Product();
        existingProduct.setProductId(1L);
        existingProduct.setProductName("testproduct");
        existingProduct.setProductType(ProductType.BOOK);
        existingProduct.setPrice(10.00);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        // Act
        ProductOutputDto updatedProductOutputDto = productService.updateProduct(1L, updatedProductDto);

        // Assert
        assertEquals(existingProduct.getProductId(), updatedProductOutputDto.getProductId());
        assertEquals(updatedProductDto.getProductName(), updatedProductOutputDto.getProductName());
        assertEquals(updatedProductDto.getProductType(), updatedProductOutputDto.getProductType());
        assertEquals(updatedProductDto.getPrice(), updatedProductOutputDto.getPrice());
    }

    @Test
    void deleteProduct() throws RecordNotFoundException {
        // Arrange
        Long productId = 1L;

        // Mock the existsById() method to return true
        when(productRepository.existsById(productId)).thenReturn(true);

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }
}