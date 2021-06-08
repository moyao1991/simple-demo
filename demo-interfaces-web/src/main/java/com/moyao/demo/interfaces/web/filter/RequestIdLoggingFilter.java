package com.moyao.demo.interfaces.web.filter;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestIdLoggingFilter extends OncePerRequestFilter {

    private static final String REQUEST_FLAG = "requestId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isError = false;
        try {
            // 如果是error请求，已经有上下文，不用设置
            if(request.getDispatcherType() != DispatcherType.ERROR){
                String requestId = Optional.ofNullable(request.getParameter(REQUEST_FLAG))
                        .orElse(IdUtil.fastSimpleUUID());
                MDC.put(REQUEST_FLAG, requestId);
            }
            filterChain.doFilter(request, response);
        }catch (Exception e){
            isError = true;
            throw e;
        }finally {
            // 容器会以forward Error的形势访问 /error路径
            // 为了保证容器中日志的跟踪，因此不清空
            if( !isError){
                MDC.clear();
            }
        }
    }


}