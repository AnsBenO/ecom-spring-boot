package com.ansbeno.start_beca.services.category;

import java.util.List;

import com.ansbeno.start_beca.dtos.CategoryDto;
import com.ansbeno.start_beca.dtos.PagedResultDto;

public interface CategoryService {

      public PagedResultDto<CategoryDto> findAll(int page, String keyword);

      public CategoryDto findById(Long id);

      public CategoryDto save(CategoryDto dto);

      public void delete(Long id);

      public List<String> findNames();

}
