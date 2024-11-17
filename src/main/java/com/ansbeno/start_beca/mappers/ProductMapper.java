package com.ansbeno.start_beca.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ansbeno.start_beca.domain.product.Product;
import com.ansbeno.start_beca.dtos.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

      ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

      // Map Product to ProductDto, converting Category to String
      @Mapping(target = "categoryName", source = "category.name")
      ProductDto toTarget(Product product);

      // Ignore category when mapping ProductDto to Product
      @Mapping(target = "category", ignore = true)
      Product toSource(ProductDto productDto);

}
