package com.ansbeno.start_beca.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ansbeno.start_beca.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

      Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
