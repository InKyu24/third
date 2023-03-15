package com.example.third.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="member")
public class NewMember {
  @Id
  @Column(name = "member_id")
  public String id;

  @ManyToOne
  @JoinColumn(name = "team_id")
  public Team team;
  public String username;
}
