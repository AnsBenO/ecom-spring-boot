package com.ansbeno.start_beca.dtos;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Validated
public class UserDto {
      private long id;
      @NotEmpty
      private String username;
      @NotEmpty
      private String email;
      @NotEmpty
      private String password;
}