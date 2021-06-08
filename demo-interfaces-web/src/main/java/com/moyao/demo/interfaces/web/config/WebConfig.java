package com.moyao.demo.interfaces.web.config;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.moyao.demo.interfaces.web.filter.RequestIdLoggingFilter;
import com.moyao.demo.interfaces.web.interceptor.UserAuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean<RequestIdLoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestIdLoggingFilter());
        registration.setName("requestIdLogFilter");
        registration.addUrlPatterns("/*");
        registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        registration.addInitParameter("paramName", "paramValue");
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/error");
    }
}
