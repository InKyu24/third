package com.example.third.filter;

import com.example.third.controller.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoginCheckFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    String requestURI = req.getRequestURI();
    String uuid = UUID.randomUUID().toString();

    try {
      log.info("로그인 인증 필터 시작 : {}, {}", requestURI, uuid);
      if(!PatternMatchUtils.simpleMatch(SessionConst.WHITE_LIST, requestURI)) { // 화이트리스트에 없는 URI라면 로그인한 사용자만 접근할 수 있게
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute(SessionConst.NAME) == null) {
          log.info("미 인증 사용자의 요청 : {}", requestURI);
          res.sendRedirect("/login?requestURI=" + requestURI);
          return;
        }
      }
      chain.doFilter(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      log.info("로그인 인증 필터 종료 : {}, {}", requestURI, uuid);
    }
  }
}
