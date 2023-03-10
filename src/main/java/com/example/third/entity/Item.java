package com.example.third.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table
@NoArgsConstructor
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
