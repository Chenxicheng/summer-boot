package com.summer.commen.config.security.jwt;

import com.summer.modules.sys.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 */
public class SecurityUserDetail extends User implements UserDetails {
//    private final Date lastPasswordResetDate;

    public SecurityUserDetail(User user) {
        if(user!=null) {
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setStatus(user.getStatus());
            this.setRoleList(user.getRoleList());
            this.setPermissionList(user.getPermissionList());
//            this.setPermissions(user.getPermissions());
        }
    }


    /**
     * 返回分配给用户的角色列表
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getPermissionList().stream()
                .map(p -> {
                    return new SimpleGrantedAuthority(p.getName());
                })
                .collect(Collectors.toList());
    }

    /**
     * 账户是否过期
     * @return
     */
//    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return User.USER_STATUS_LOCK.equals(this.getStatus()) ? false : true;
    }

    /**
     * 密码是否未过期
     * @return
     */
//    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否启用
     * @return
     */
//    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return User.USER_STATUS_NORMAL.equals(this.getStatus()) ? true: false;
    }

}
