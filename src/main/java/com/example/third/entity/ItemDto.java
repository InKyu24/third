package com.example.third.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
  private Long id;
  private String name;
  private Integer price;
  private Integer quantity;
}
