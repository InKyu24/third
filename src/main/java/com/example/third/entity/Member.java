package com.example.third.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
  private Long id;
  private String loginId, name, password;

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", loginId='" + loginId + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
