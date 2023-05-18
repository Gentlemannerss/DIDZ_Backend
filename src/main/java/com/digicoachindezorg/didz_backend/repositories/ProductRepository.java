package com.digicoachindezorg.didz_backend.repositories;

import com.digicoachindezorg.didz_backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}