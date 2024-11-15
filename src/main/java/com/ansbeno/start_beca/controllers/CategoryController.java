package com.ansbeno.start_beca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ansbeno.start_beca.dtos.CategoryDto;
import com.ansbeno.start_beca.dtos.PagedResultDto;
import com.ansbeno.start_beca.services.category.CategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/categories")
class CategoryController {

      private final CategoryServiceImpl categoryService;

      @GetMapping
      public String getAllCategories(
                  @RequestParam(required = false, defaultValue = "") String keyword,
                  @RequestParam(defaultValue = "1") int page,
                  Model model) {
            if (page < 1) {
                  page = 1;
            }

            PagedResultDto<CategoryDto> categories = categoryService.findAll(page, keyword);

            model.addAttribute("keyword", keyword);
            model.addAttribute("categories", categories);
            return "views/categories/list-categories";
      }

}
