package com.summer.commen.config.exception;

import com.summer.commen.utils.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局处理异常信息
 * @Author: Dashwood
 * @Date: 2018/11/20 22:33
 * @Version: 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResultJSON handleException(HttpServletRequest req, Exception e) {
        String errorMsg = "Exception";
        if (e!=null){
            errorMsg=e.getMessage();
            log.error(e.toString());
        }
        return ResultJSON.setErrorMsg(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMsg).put("url", req.getRequestURL().toString());
    }
}
