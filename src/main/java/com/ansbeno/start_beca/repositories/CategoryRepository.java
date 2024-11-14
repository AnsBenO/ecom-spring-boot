package com.ansbeno.start_beca.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ansbeno.start_beca.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

      Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
