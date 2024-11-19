package com.ansbeno.start_beca.domain.user;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ansbeno.start_beca.dtos.UserDto;
import com.ansbeno.start_beca.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

      private final UserRepository userRepository;
      private final UserMapper userMapper;
      private final PasswordEncoder passwordEncoder;

      @Override
      public UserDto findById(Long id) {
            return userMapper.toTarget(userRepository.findById(id).orElseThrow());
      }

      @Override
      public List<UserDto> findAll() {
            return userRepository.findAll().stream().map(userMapper::toTarget).toList();
      }

      @Override
      public UserDto save(UserDto user) {
            UserEntity userEntity = userMapper.toSource(user);
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRole(Role.ADMIN);
            userRepository.save(userEntity);
            return user;
      }

      @Override
      public void delete(Long id) {
            userRepository.deleteById(id);
      }

      @Override
      public boolean existsByEmail(String email) {
            return userRepository.existsUserEntityByEmail(email);
      }

}
