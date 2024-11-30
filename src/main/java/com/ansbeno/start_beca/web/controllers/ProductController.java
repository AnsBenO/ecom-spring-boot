package com.ansbeno.start_beca.web.controllers;

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

import com.ansbeno.start_beca.domain.category.CategoryService;
import com.ansbeno.start_beca.domain.product.ProductService;
import com.ansbeno.start_beca.dtos.PagedResultDto;
import com.ansbeno.start_beca.dtos.ProductDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
class ProductController {

      private final ProductService productService;
      private final CategoryService categoryService;

      @GetMapping
      String getAllProducts(@RequestParam(defaultValue = "1") int page,
                  @RequestParam(required = false, defaultValue = "") String keyword,
                  @RequestParam(required = false, defaultValue = "") String category,
                  HttpServletRequest request,
                  Model model) {
            // Fetch products based on keyword, category, and page
            PagedResultDto<ProductDto> products = productService.findAll(page, keyword, category);

            model.addAttribute("products", products);
            model.addAttribute("categories", categoryService.findNames());
            model.addAttribute("category", category);
            model.addAttribute("keyword", keyword);

            // Check if the request is an HTMX request
            boolean isHtmxRequest = request.getHeader("HX-Request") != null;

            // Return partial HTML if it's an HTMX request
            if (isHtmxRequest) {
                  return "views/products/list-products :: products-table";
            }

            // Otherwise, return the full page
            return "views/products/list-products";
      }

      @GetMapping("/{id}")
      String getProductById(Model model) {
            return "";
      }

      @GetMapping("/add")
      String showAddProduct(Model model) {
            ProductDto productDto = new ProductDto();
            model.addAttribute("product", productDto);
            return "views/products/add-product";
      }

      @PostMapping
      String createProduct(@RequestBody ProductDto productDto) {
            ProductDto savedProduct = productService.save(productDto);
            return "";
      }

      @PutMapping("/{id}")
      String updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
            productDto.setId(id);
            ProductDto updatedProduct = productService.save(productDto);
            return "";
      }

      @DeleteMapping("/{id}")
      String deleteProduct(@PathVariable Long id) {
            productService.delete(id);
            return "";
      }
}
