package com.summer.commen.config.security.filter;

import com.summer.commen.utils.ResponseUtils;
import com.summer.commen.utils.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败后处理
 * @Author: 陈喜骋
 * @Date: 2018/7/21 11:50
 * @Version: 1.0
 */
@Slf4j
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            /*String username = request.getParameter("username");
            recordLoginTime(username);
            String key = "loginTimeLimit:"+username;
            String value = redisTemplate.opsForValue().get(key);
            if(StrUtil.isBlank(value)){
                value = "0";
            }
            //获取已登录错误次数
            int loginFailTime = Integer.parseInt(value);
            int restLoginTime = loginTimeLimit - loginFailTime;*/
            ResponseUtils.out(response, ResultJSON.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "用户名或密码错误"));
        } else if (e instanceof DisabledException) {
            ResponseUtils.out(response, ResultJSON.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"账户被禁用，请联系管理员"));
        } else {
            ResponseUtils.out(response, ResultJSON.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"登录失败"));
        }


    }
}
