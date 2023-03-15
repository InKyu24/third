package com.example.third.entity;

import javax.persistence.*;

@Entity
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  public Long id;

  @Column
  public String name;
}
