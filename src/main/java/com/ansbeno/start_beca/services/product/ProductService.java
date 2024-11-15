package com.ansbeno.start_beca.services.product;

import com.ansbeno.start_beca.dtos.PagedResultDto;
import com.ansbeno.start_beca.dtos.ProductDto;

public interface ProductService {

      public PagedResultDto<ProductDto> findAll(int page, String keyword, String category);

      public ProductDto findById(Long id);

      public ProductDto save(ProductDto dto);

      public void delete(Long id);

}
