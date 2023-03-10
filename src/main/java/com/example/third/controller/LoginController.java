package com.example.third.controller;

import com.example.third.entity.Member;
import com.example.third.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginController {
  MemberService memberService;

  @Autowired
  public LoginController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/login")
  public String login(Model model) {
    Member member = new Member();
    model.addAttribute("member", member);
    return "member/login";
  }

  @PostMapping("/login")
  public String login(Member member, HttpServletResponse res, RedirectAttributes redirectAttributes) {
    if (memberService.login(member)) {
      Optional<Member> loginMember = memberService.findMember(member.getLoginId());
      Cookie memberId = new Cookie("memberId", String.valueOf(loginMember.get().getId()));
      res.addCookie(memberId);
      return "redirect:/";
    }
    redirectAttributes.addFlashAttribute("message", "로그인 실패");
    return "redirect:/";
  }
  @GetMapping("/logout")
  public String logout(HttpServletResponse res, RedirectAttributes redirectAttributes) {
    Cookie cookie = new Cookie("memberId", null);
    cookie.setMaxAge(0);
    res.addCookie(cookie);
    redirectAttributes.addFlashAttribute("message", "로그아웃 완료");
    return "redirect:/";
  }
}