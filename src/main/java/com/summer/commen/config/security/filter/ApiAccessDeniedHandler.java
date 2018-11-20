package com.summer.commen.config.security.filter;

import com.summer.commen.utils.ResponseUtils;
import com.summer.commen.utils.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Api接口权限拒绝操作
 * @Author: Dashwood
 * @Date: 2018/7/21 12:10
 * @Version: 1.0
 */
@Slf4j
@Component
public class ApiAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtils.out(response, ResultJSON.setErrorMsg(HttpServletResponse.SC_FORBIDDEN, "没有权限"));
    }
}
