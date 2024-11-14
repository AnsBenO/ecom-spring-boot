package com.ansbeno.start_beca;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ansbeno.start_beca.entities.Category;
import com.ansbeno.start_beca.entities.Product;
import com.ansbeno.start_beca.repositories.CategoryRepository;
import com.ansbeno.start_beca.repositories.ProductRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Initializer {
      private final CategoryRepository categoryRepository;
      private final ProductRepository productRepository;

      @PostConstruct
      public void insertData() {
            if (categoryRepository.count() == 0 && productRepository.count() == 0) {
                  // Create and save categories
                  Category electronics = new Category(null, "Electronics");
                  Category books = new Category(null, "Books");
                  Category clothing = new Category(null, "Clothing");
                  Category home = new Category(null, "Home");

                  categoryRepository.saveAll(List.of(electronics, books, clothing, home));

                  // Create and save products, assigning them to categories
                  productRepository.saveAll(List.of(
                              new Product(null, "Laptop", "High performance laptop", 1200.0, electronics),
                              new Product(null, "Smartphone", "Latest model smartphone", 800.0, electronics),
                              new Product(null, "Tablet", "10-inch tablet", 300.0, electronics),
                              new Product(null, "Headphones", "Noise-cancelling headphones", 150.0, electronics),
                              new Product(null, "Camera", "DSLR camera", 500.0, electronics),

                              new Product(null, "Novel", "Bestselling novel", 20.0, books),
                              new Product(null, "Textbook", "University level textbook", 50.0, books),
                              new Product(null, "Cookbook", "Delicious recipes", 25.0, books),
                              new Product(null, "Comics", "Popular comic book", 15.0, books),
                              new Product(null, "Biography", "Inspiring life story", 30.0, books),

                              new Product(null, "T-Shirt", "Cotton t-shirt", 10.0, clothing),
                              new Product(null, "Jeans", "Classic blue jeans", 40.0, clothing),
                              new Product(null, "Jacket", "Warm winter jacket", 80.0, clothing),
                              new Product(null, "Sneakers", "Comfortable sneakers", 60.0, clothing),
                              new Product(null, "Hat", "Stylish hat", 15.0, clothing),

                              new Product(null, "Sofa", "Comfortable sofa", 300.0, home),
                              new Product(null, "Lamp", "Decorative lamp", 40.0, home),
                              new Product(null, "Rug", "Soft area rug", 80.0, home),
                              new Product(null, "Coffee Maker", "Brews fresh coffee", 100.0, home),
                              new Product(null, "Blender", "High-speed blender", 60.0, home)));
            }
      }

}
