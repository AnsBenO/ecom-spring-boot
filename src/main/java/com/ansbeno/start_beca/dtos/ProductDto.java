package com.ansbeno.start_beca.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProductDto {
      private Long id;
      private String name;
      private String description;
      private Double price;
      private String image;
      private String categoryName;

}
