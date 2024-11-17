package com.ansbeno.start_beca.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

      @Query("""
                  SELECT p FROM Product p
                  JOIN p.category c
                  WHERE c.name = :categoryName
                  AND LOWER(p.name) LIKE CONCAT('%', LOWER(:keyword), '%')
                  """)
      Page<Product> findByNameAndCategory(String keyword, String categoryName, Pageable pageable);

      Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

}
