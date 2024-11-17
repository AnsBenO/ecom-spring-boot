package com.ansbeno.start_beca.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ansbeno.start_beca.domain.category.CategoryService;
import com.ansbeno.start_beca.dtos.CategoryDto;
import com.ansbeno.start_beca.dtos.PagedResultDto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/categories")
class CategoryController {

      private final CategoryService categoryService;

      @GetMapping
      public String getAllCategories(
                  @RequestParam(required = false, defaultValue = "") String keyword,
                  @RequestParam(defaultValue = "1") int page,
                  HttpServletRequest request,
                  Model model) {

            PagedResultDto<CategoryDto> categories = categoryService.findAll(page, keyword);

            model.addAttribute("keyword", keyword);
            model.addAttribute("categories", categories);

            // Check if the request is an HTMX request
            boolean isHtmxRequest = request.getHeader("HX-Request") != null;

            if (isHtmxRequest) {
                  return "views/categories/list-categories :: categories-table";
            }

            return "views/categories/list-categories";
      }

}
