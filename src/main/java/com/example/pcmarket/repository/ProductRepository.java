package com.example.pcmarket.repository;

import com.example.pcmarket.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {
    boolean existsByName(String name);
    boolean existsByDescription(String description);
}
