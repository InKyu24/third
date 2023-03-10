package com.example.third.controller;

import com.example.third.controller.session.MemberSession;
import com.example.third.controller.session.SessionConst;
import com.example.third.entity.Member;
import com.example.third.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
  MemberService memberService;

  @Autowired
  public HomeController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/")
  public String home(
      // @CookieValue(value = SessionConst.COOKIE_NAME, required = false) Long memberId,
      HttpServletRequest req, Model model) {
    HttpSession session = req.getSession(false);
    if (session != null) {
      // Member member = memberService.findMemberById(memberId).get();
      // member.setPassword(null);
      // model.addAttribute("member", member);
      MemberSession memberSession = (MemberSession) session.getAttribute(SessionConst.NAME);

      model.addAttribute("member", memberSession);
    }
    return "home";
  }
}
