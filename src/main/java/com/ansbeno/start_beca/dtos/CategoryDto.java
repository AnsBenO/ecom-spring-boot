package com.ansbeno.start_beca.dtos;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
      private Long id;
      private String name;

}
