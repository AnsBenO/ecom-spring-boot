package com.ansbeno.start_beca.domain.user;

import java.util.List;

import com.ansbeno.start_beca.dtos.UserDto;

public interface UserService {
      UserDto findById(Long id);

      List<UserDto> findAll();

      UserDto save(UserDto user);

      void delete(Long id);

      // UserDto findByEmail(String email);

      boolean existsByEmail(String email);

}
