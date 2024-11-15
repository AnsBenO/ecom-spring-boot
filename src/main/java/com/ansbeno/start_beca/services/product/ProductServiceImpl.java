package com.ansbeno.start_beca.services.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ansbeno.start_beca.dtos.PagedResultDto;
import com.ansbeno.start_beca.dtos.ProductDto;
import com.ansbeno.start_beca.entities.Product;
import com.ansbeno.start_beca.mappers.ProductMapper;
import com.ansbeno.start_beca.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

      private final ProductRepository productRepository;

      private final ProductMapper productMapper;

      @Override
      public PagedResultDto<ProductDto> findAll(int pageNumber, String keyword, String category) {
            Sort sort = Sort.by("id").ascending();
            pageNumber = pageNumber <= 1 ? 0 : pageNumber - 1;
            Pageable pageable = PageRequest.of(pageNumber, 10, sort);

            Page<Product> productPage = category.length() == 0
                        ? productRepository.findByNameContainingIgnoreCase(keyword, pageable)
                        : productRepository.findByNameAndCategory(keyword, category, pageable);
            return PagedResultDto.<ProductDto>builder()
                        .data(productPage.toList()
                                    .stream()
                                    .map(productMapper::toTarget).toList())
                        .totalElements(productPage.getTotalElements())
                        .pageNumber(pageNumber + 1)
                        .totalPages(productPage.getTotalPages())
                        .isFirst(productPage.isFirst())
                        .isLast(productPage.isLast())
                        .hasNext(productPage.hasNext())
                        .hasPrevious(productPage.hasPrevious())
                        .build();

      }

      @Override
      public ProductDto findById(Long id) {
            return productMapper.toTarget(productRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + id)));
      }

      @Override
      public ProductDto save(ProductDto dto) {
            Product productToSave = productMapper.toSource(dto);
            productRepository.save(productToSave);
            return dto;
      }

      @Override
      public void delete(Long id) {
            Product productToDelete = productRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
            productRepository.delete(productToDelete);
      }

}
