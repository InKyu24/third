package com.example.third.controller;

import com.example.third.controller.session.SessionConst;
import com.example.third.controller.session.MemberSession;
import com.example.third.entity.Member;
import com.example.third.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
  public String login(Member member, HttpServletResponse res, HttpServletRequest req, RedirectAttributes redirectAttributes, String requestURI) {
    if (memberService.login(member)) {
      Member loginMember = memberService.findMember(member.getLoginId()).get();

      // Cookie memberId = new Cookie(CookieConst.COOKIE_NAME, String.valueOf(loginMember.getId()));
      //res.addCookie(memberId);

      MemberSession memberSession = new MemberSession();
      memberSession.setId(loginMember.getId());
      memberSession.setLoginId(loginMember.getLoginId());
      memberSession.setName(loginMember.getName());

      HttpSession session = req.getSession(true);
      session.setAttribute(SessionConst.NAME, memberSession);

      return "redirect:" +requestURI;
    }
    redirectAttributes.addFlashAttribute("message", "로그인 실패");
    return "redirect:/";
  }
  @PostMapping("/logout")
  public String logout(HttpServletResponse res, HttpServletRequest req, RedirectAttributes redirectAttributes) {
    // Cookie cookie = new Cookie(SessionConst.COOKIE_NAME, null);
    // cookie.setMaxAge(0);
    // res.addCookie(cookie);

    HttpSession session = req.getSession();
    session.invalidate();

    redirectAttributes.addFlashAttribute("message", "로그아웃 완료");
    return "redirect:/";
  }
}