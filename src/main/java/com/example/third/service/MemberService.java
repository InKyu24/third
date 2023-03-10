package com.example.third.service;

import com.example.third.entity.Member;
import com.example.third.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  @Transactional
  public Long join(Member member) throws IllegalStateException { // memberController에서 예외 처리해줘야 함
    validateDuplicateLoginId(member.getLoginId());
    return memberRepository.save(member).getId();
  }

  public void validateDuplicateLoginId(String loginId) {
    if (!findMember(loginId).isEmpty()) {// 동일한 로그인 아이디가 존재한다는 것
      throw new IllegalStateException("이미 존재하는 아이디입니다.");
    }
  }

  public Optional<Member> findMember(String loginId) {
    Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
    System.out.println(byLoginId);
    return byLoginId;
  }

  public Optional<Member> findMemberById(Long id) {
    return memberRepository.findById(id);
  }

  public boolean login(Member member) {
    AtomicBoolean result = new AtomicBoolean(false);
    findMember(member.getLoginId()).ifPresent(thisMember -> {
      if (member.getPassword().equals(thisMember.getPassword())) {
        result.set(true);
      }
    });
    return result.get();
  }
}