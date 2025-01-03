package com.ansbeno.start_beca.security;

import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ansbeno.start_beca.domain.user.UserEntity;
import com.ansbeno.start_beca.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
      private final UserRepository userRepository;

      @Override
      public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

            Optional<UserEntity> user = userRepository.findByEmail(email);
            if (user.isPresent()) {

                  UserEntity foundUser = user.get();

                  var authorities = foundUser.getRole().getPermissions().stream()
                              .map(permission -> new SimpleGrantedAuthority(permission.toString())).toList();

                  return new User(
                              foundUser.getUsername(),
                              foundUser.getPassword(),
                              authorities);
            } else {
                  throw new UsernameNotFoundException("Invalid Credentials");
            }

      }

}
