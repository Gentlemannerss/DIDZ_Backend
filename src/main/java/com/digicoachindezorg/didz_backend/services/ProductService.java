package com.digicoachindezorg.didz_backend.services;

import com.digicoachindezorg.didz_backend.dtos.input.ProductInputDto;
import com.digicoachindezorg.didz_backend.dtos.output.ProductOutputDto;
import com.digicoachindezorg.didz_backend.exceptions.RecordNotFoundException;
import com.digicoachindezorg.didz_backend.models.Product;
import com.digicoachindezorg.didz_backend.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductOutputDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::transferProductToProductOutputDto)
                .collect(Collectors.toList());
    }

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

        // Update the fields of the existing product
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
        /*BeanUtils.copyProperties(productDto, product);*/
        productDto.setProductId(product.getProductId()); //Alleen bij een outputDTO
        productDto.setProductName(product.getProductName());
        productDto.setReviews(product.getReviews());
        productDto.setPrice(product.getPrice());
        productDto.setProductType(product.getProductType());
        productDto.setInvoice(product.getInvoice());
        productDto.setStudyGroup(product.getStudyGroup());
        return productDto;
    }

    private Product transferProductInputDtoToProduct(ProductInputDto productDto) {
        Product product = new Product();
        /*BeanUtils.copyProperties(productDto, product); Did not work for Update*/
        if (productDto.getProductName()!=null) {
            product.setProductName(productDto.getProductName());
        }
        if (productDto.getReviews()!=null) {
            product.setReviews(productDto.getReviews());
        }
        if (productDto.getPrice()!=null) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getProductType()!=null) {
            product.setProductType(productDto.getProductType());
        }
        if (productDto.getInvoice()!=null) {
            product.setInvoice(productDto.getInvoice());
        }
        if (productDto.getStudyGroup()!=null) {
            product.setStudyGroup(productDto.getStudyGroup());
        }
        return product;
    }

    private Product updateProductInputDtoToProduct(ProductInputDto productDto, Product product) {
        if (productDto.getProductName()!=null) {
            product.setProductName(productDto.getProductName());
        }
        if (productDto.getReviews()!=null) {
            product.setReviews(productDto.getReviews());
        }
        if (productDto.getPrice()!=null) {
            product.setPrice(productDto.getPrice());
        }
        if (productDto.getProductType()!=null) {
            product.setProductType(productDto.getProductType());
        }
        if (productDto.getInvoice()!=null) {
            product.setInvoice(productDto.getInvoice());
        }
        if (productDto.getStudyGroup()!=null) {
            product.setStudyGroup(productDto.getStudyGroup());
        }
        return product;
    }
}