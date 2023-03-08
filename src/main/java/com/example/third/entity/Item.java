package com.example.third.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Item {
  private Long id;
  private String name;
  private Integer price;
  private Integer quantity;

  private Integer cost;

  public Item(String name, Integer price, Integer quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }
}
