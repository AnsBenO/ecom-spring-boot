package com.ansbeno.start_beca.services.category;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ansbeno.start_beca.dtos.CategoryDto;
import com.ansbeno.start_beca.dtos.PagedResultDto;
import com.ansbeno.start_beca.entities.Category;
import com.ansbeno.start_beca.mappers.CategoryMapper;
import com.ansbeno.start_beca.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

      private final CategoryRepository categoryRepository;

      private final CategoryMapper categoryMapper;

      @Override
      public PagedResultDto<CategoryDto> findAll(int pageNumber, String keyword) {
            Sort sort = Sort.by("id").ascending();
            pageNumber = pageNumber <= 1 ? 0 : pageNumber - 1;
            Pageable pageable = PageRequest.of(pageNumber, 10, sort);
            Page<Category> categoryPage = categoryRepository.findByNameContainingIgnoreCase(keyword, pageable);
            return PagedResultDto.<CategoryDto>builder()
                        .data(categoryPage.toList()
                                    .stream()
                                    .map(categoryMapper::toTarget).toList())
                        .totalElements(categoryPage.getTotalElements())
                        .pageNumber(pageNumber + 1)
                        .totalPages(categoryPage.getTotalPages())
                        .isFirst(categoryPage.isFirst())
                        .isLast(categoryPage.isLast())
                        .hasNext(categoryPage.hasNext())
                        .hasPrevious(categoryPage.hasPrevious())
                        .build();
      }

      @Override
      public CategoryDto findById(Long id) {
            return categoryMapper.toTarget(categoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + id)));
      }

      @Override
      public CategoryDto save(CategoryDto dto) {
            Category categoryToSave = categoryMapper.toSource(dto);
            categoryRepository.save(categoryToSave);
            return dto;
      }

      @Override
      public void delete(Long id) {
            Category categoryToDelete = categoryRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
            categoryRepository.delete(categoryToDelete);
      }

      @Override
      public List<String> findNames() {
            return categoryRepository.findAllNames();
      }

}
