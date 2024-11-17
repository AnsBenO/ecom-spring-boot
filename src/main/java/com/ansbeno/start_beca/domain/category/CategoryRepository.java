package com.ansbeno.start_beca.domain.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

      Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);

      @Query("SELECT c.name FROM Category c")
      List<String> findAllNames();

}
