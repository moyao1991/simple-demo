package com.moyao.demo.interfaces.web;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.NestedServletException;
import org.springframework.web.util.WebUtils;
import com.moyao.demo.common.exception.BizDataException;
import com.moyao.demo.common.exception.BizErrorCode;
import com.moyao.demo.common.exception.BizException;
import com.moyao.demo.interfaces.web.common.Result;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RestController
@Slf4j
public class DefaultExceptionHandler implements ErrorController {

    private static final String ERROR_PATH = "/error";

    private static final String ERROR_MSG = "未知异常";

    private static final String ERROR_PAGE = "hello error page!";

    /**
     *  日志上下文标识
     *  @see com.moyao.demo.interfaces.web.filter.RequestIdLoggingFilter
     */
    private static final String REQUEST_FLAG = "requestId";

    @ExceptionHandler(Exception.class)
    public Result handleRuntimeException(Exception e) {
        if(e instanceof BizDataException){
            return handleBizDataException((BizDataException) e);
        }
        if(e instanceof BizException){
            return handleBizException((BizException) e);
        }
        String message = StringUtils.isEmpty(e.getMessage()) ? ERROR_MSG : e.getMessage();
        log.error(message, e);
        return handleException(e);
    }

    @RequestMapping(ERROR_PATH)
    public Result error(HttpServletRequest request) {
        Exception exp = (Exception) request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE);
        if (exp == null) {
            return Result.success(ERROR_PAGE);
        }
        Exception realException = exp instanceof NestedServletException ? (Exception) NestedExceptionUtils.getRootCause(exp) : exp;
        if (realException instanceof BizException) {
            return handleBizException((BizException) realException);
        }
        return handleException(realException);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    private Result handleBizException(BizException e) {
        return Result.fail(e.getCode(), e.getMsg());
    }

    private Result handleBizDataException(BizDataException e) {
        return Result.fail(e.getCode(), e.getMsg(), e.getData());
    }

    private Result handleException(Exception e) {
        String message = StringUtils.isEmpty(e.getMessage()) ? ERROR_MSG : e.getMessage();
        String errorId = MDC.get(REQUEST_FLAG);
        return Result.fail(BizErrorCode.UNKNOWN_CODE, message, errorId);
    }

}
