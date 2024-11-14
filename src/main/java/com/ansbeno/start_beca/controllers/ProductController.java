package com.ansbeno.start_beca.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ansbeno.start_beca.dtos.PagedResultDto;
import com.ansbeno.start_beca.dtos.ProductDto;
import com.ansbeno.start_beca.entities.Product;
import com.ansbeno.start_beca.services.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
class ProductController {

      private final ProductService productService;

      @GetMapping
      public String getAllProducts(@RequestParam(defaultValue = "1") int page,
                  @RequestParam(required = false, defaultValue = "") String keyword,
                  Model model) {
            if (page < 1) {
                  page = 1;
            }

            PagedResultDto<ProductDto> products = productService.findAll(page, keyword);

            if (page > products.totalPages()) {
                  return "redirect:/products?page=" + products.totalPages() + "&keyword=" + keyword;
            }

            model.addAttribute("products", products);
            model.addAttribute("keyword", keyword);
            return "views/products/list-products";
      }

      @GetMapping("/{id}")
      public String getProductById(Model model) {
            return "";
      }

      @PostMapping
      public String createProduct(@RequestBody ProductDto productDto) {
            ProductDto savedProduct = productService.save(productDto);
            return "";
      }

      @PutMapping("/{id}")
      public String updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
            productDto.setId(id);
            ProductDto updatedProduct = productService.save(productDto);
            return "";
      }

      @DeleteMapping("/{id}")
      public String deleteProduct(@PathVariable Long id) {
            productService.delete(id);
            return "";
      }
}
