package com.ansbeno.start_beca.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ansbeno.start_beca.domain.category.Category;
import com.ansbeno.start_beca.dtos.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
      CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

      CategoryDto toTarget(Category s);

      Category toSource(CategoryDto c);

}
