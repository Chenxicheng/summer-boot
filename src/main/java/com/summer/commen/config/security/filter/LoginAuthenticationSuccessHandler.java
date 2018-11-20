package com.summer.commen.config.security.filter;

import com.summer.commen.config.security.jwt.JWTUtils;
import com.summer.commen.config.security.jwt.SecurityUserDetail;
import com.summer.commen.utils.ResponseUtils;
import com.summer.commen.utils.ResultJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 登录成功后操作类
 * 主要用户生成jwt
 * @Author: 陈喜骋
 * @Version: 1.0
 * @Date: 2018/07/21
 */
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JWTUtils jwtUtils;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        SecurityUserDetail userDetail = (SecurityUserDetail)authentication.getPrincipal();

        List<SimpleGrantedAuthority> authorityList =  (List<SimpleGrantedAuthority>)userDetail.getAuthorities();

        String token = jwtUtils.generateToken(userDetail.getUsername(), userDetail.getId(), authorityList);

        ResponseUtils.out(response, ResultJSON.setOkMsg().put("token", JWTUtils.TOKEN_PREFIX+token));

    }
}
