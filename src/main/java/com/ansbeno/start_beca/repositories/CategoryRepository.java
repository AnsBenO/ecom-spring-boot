package com.ansbeno.start_beca.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ansbeno.start_beca.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

      Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

      @Query("SELECT c.name FROM Category c")
      List<String> findAllNames();

}
