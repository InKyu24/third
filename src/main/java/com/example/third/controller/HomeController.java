package com.example.third.controller;

import com.example.third.entity.Member;
import com.example.third.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  MemberService memberService;

  @Autowired
  public HomeController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/")
  public String home(@CookieValue(value = "memberId", required = false) Long memberId, Model model) {
    if (memberId != null) {
      Member member = memberService.findMemberById(memberId).get();
      member.setPassword(null);
      model.addAttribute("member", member);
    }
    return "home";
  }
}
