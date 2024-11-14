package com.ansbeno.start_beca.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ansbeno.start_beca.dtos.CategoryDto;
import com.ansbeno.start_beca.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
      CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

      CategoryDto toTarget(Category s);

      Category toSource(CategoryDto c);

}
