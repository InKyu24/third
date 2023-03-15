package com.example.third.repository;

import com.example.third.entity.NewMember;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class JpaNewMemberRepositoryTest {

  final EntityManager em;

  @Autowired
  JpaNewMemberRepositoryTest(EntityManager em) {
    this.em = em;
  }

  @Test
  void 동일한_트렌젝션_엔티티_동일성_테스트() {
    String id = "1111";
    NewMember m1 = em.find(NewMember.class, id);
    NewMember m2 = em.find(NewMember.class, id);

    Assertions.assertThat(m1).isEqualTo(m2);

  }
}