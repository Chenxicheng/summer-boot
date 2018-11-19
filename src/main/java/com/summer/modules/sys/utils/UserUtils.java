package com.summer.modules.sys.utils;

import com.summer.commen.utils.SpringContextHolder;
import com.summer.commen.utils.SpringUtils;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtils {

    private static SecurityUtils securityUtils = SpringUtils.getBean(SecurityUtils.class);

    public static User getUser() {
        return securityUtils.getCurrUser();
    }


}
