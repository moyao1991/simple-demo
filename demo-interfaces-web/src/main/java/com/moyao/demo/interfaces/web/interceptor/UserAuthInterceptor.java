package com.moyao.demo.interfaces.web.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.base.Charsets;
import com.moyao.demo.common.exception.AuthBizException;
import com.moyao.demo.interfaces.web.common.UserHolder;

public class UserAuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException, IOException {

        try {
            String encryptUserId = request.getParameter("id");
            UserHolder.set(Long.parseLong(new String(Base64.decodeBase64(encryptUserId), Charsets.UTF_8)));
            return true;
        } catch (Exception e) {
            // ignore
        }

        // 发现是资源路径直接报错
        if (handler != null && !(handler instanceof HandlerMethod)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }

        throw new AuthBizException();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        UserHolder.remove();
    }
}
