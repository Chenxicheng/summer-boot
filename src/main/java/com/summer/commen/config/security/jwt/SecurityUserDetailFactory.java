package com.summer.commen.config.security.jwt;

import com.summer.modules.sys.entity.User;

/**
 * SecurityUserDetail工厂
 */
public class SecurityUserDetailFactory {

    private SecurityUserDetailFactory() {
    }

    public static SecurityUserDetail create(User user) {
        return new SecurityUserDetail(user);
    }

}
