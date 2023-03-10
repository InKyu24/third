package com.example.third.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info("LogFilter doFilter");
    HttpServletRequest req = (HttpServletRequest) request;
    String requestURI = req.getRequestURI();
    String uuid = UUID.randomUUID().toString();
    try {
      log.info("requestURI: {}, uuid: {}", requestURI, uuid);
      chain.doFilter(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } finally {
      log.info("requestURI: {}, uuid: {}", requestURI, uuid);
    }
  }

}
