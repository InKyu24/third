package com.example.third.repository;

import com.example.third.entity.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

  private static final Map<Long, Member> memberMap = new HashMap<>();
  private static Long seq = 0L;

  @Override
  public Member save(Member member) {
    member.setId(++seq);
    memberMap.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(memberMap.get(id));
  }

  @Override
  public Optional<Member> findByLoginId(String loginId) {
    return Optional.empty();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(memberMap.values());
  }

  @Override
  public void update(Long id, Member member) {
    findById(id).ifPresent(findMember -> {
      member.setName(findMember.getName());
      member.setPassword(findMember.getPassword());
    });
    memberMap.put(id, member);
  }
}
