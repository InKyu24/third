package com.example.third.repository;

import com.example.third.entity.NewMember;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;

public class JpaNewMemberRepository {
  private final EntityManager entityManager;

  @Autowired
  public JpaNewMemberRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public String save() {
    NewMember member = new NewMember();
    member.setId("4");
    member.setUsername("jpa_test");
    entityManager.persist(member);
    return member.getId();
  }
}
