package com.digicoachindezorg.didz_backend.controllers;

import com.digicoachindezorg.didz_backend.dtos.input.ProductInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ProductOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<ProductOutputDto> createProduct(@RequestBody ProductInputDto productDto) {
        ProductOutputDto createdProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductOutputDto> updateProduct(@PathVariable Long id, @RequestBody ProductInputDto productDtoToUpdate) throws RecordNotFoundException {
        ProductOutputDto updatedProduct = productService.updateProduct(id, productDtoToUpdate);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws RecordNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductOutputDto> getProduct(@PathVariable Long id) throws RecordNotFoundException {
        ProductOutputDto product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
    @GetMapping
    public ResponseEntity<List<ProductOutputDto>> getAllProducts() {
        List<ProductOutputDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}

/*
    - Moeten de Id hier niet productId zijn, (duplicates in code?)
*/