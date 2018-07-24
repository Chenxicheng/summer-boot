package com.summer.modules.sys.utils;

import com.summer.commen.utils.SpringContextHolder;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {

    private static UserService userService = SpringContextHolder.getBean(UserService.class);

    public static User getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getByUsername(userDetails.getUsername());
    }


}
