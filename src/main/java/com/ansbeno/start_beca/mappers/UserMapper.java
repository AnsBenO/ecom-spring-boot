package com.ansbeno.start_beca.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ansbeno.start_beca.domain.user.UserEntity;
import com.ansbeno.start_beca.dtos.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
      UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

      public UserDto toTarget(UserEntity user);

      @Mapping(target = "createdAt", ignore = true)
      @Mapping(target = "updatedAt", ignore = true)
      @Mapping(target = "role", ignore = true)
      public UserEntity toSource(UserDto dto);
}
