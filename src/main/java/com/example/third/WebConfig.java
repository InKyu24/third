package com.example.third;

import com.example.third.filter.LogFilter;
import com.example.third.filter.LoginCheckFilter;
import com.example.third.interceptor.LogInterceptor;
import com.example.third.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor()).order(1).addPathPatterns("/**").excludePathPatterns("/css/**", "/", "/favicon.ico", "/error");
    registry.addInterceptor(new LoginCheckInterceptor()).order(2).addPathPatterns("/**").excludePathPatterns("/", "/login", "/join", "/logout", "/css/**", "/favicon.ico", "/error");
  }

//  @Bean
//  public FilterRegistrationBean LogFilter() {
//    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//    filterRegistrationBean.setFilter(new LogFilter());
//    filterRegistrationBean.setOrder(1);
//    filterRegistrationBean.addUrlPatterns("/*"); // 모든 요청에 대해 적용
//    return filterRegistrationBean;
//  }
//
//  @Bean
//  public FilterRegistrationBean LoginCheckFilter() {
//    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//    filterRegistrationBean.setFilter(new LoginCheckFilter());
//    filterRegistrationBean.setOrder(2);
//    filterRegistrationBean.addUrlPatterns("/*"); // 모든 요청에 대해 적용1
//    return filterRegistrationBean;
//  }
}
