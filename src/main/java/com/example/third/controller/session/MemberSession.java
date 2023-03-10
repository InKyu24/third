package com.example.third.controller.session;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberSession {
  private Long id;
  private String loginId, name;
}
