package com.summer.commen.config.security.jwt;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @Author: Dashwood
 * @Date: 2018/7/21 21:53
 * @Version: 1.0
 */
@Getter
@Setter
public class CurrentUser extends User {
    private String id;

    public CurrentUser(String username, String password,String id, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }
}
