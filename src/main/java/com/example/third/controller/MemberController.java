package com.example.third.controller;

import com.example.third.entity.Member;
import com.example.third.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {

  MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/join")
  public ModelAndView join() {
    ModelAndView modelAndView = new ModelAndView("member/join");
    modelAndView.addObject("member", new Member());
    return modelAndView;
  }

  @PostMapping("/join")
  public String join(Member member, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("message", "회원가입 성공");
    memberService.join(member);
    return "redirect:/";
  }
}
