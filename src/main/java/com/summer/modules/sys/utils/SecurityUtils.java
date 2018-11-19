package com.summer.modules.sys.utils;

import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityUtils {
    @Autowired
    private UserService userService;
    /**
     * 获取当前登录用户
     * @return
     */
    public User getCurrUser(){
        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userDetails == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return userService.findByUsername(userDetails.getUsername());
    }

}
