package com.summer.modules.sys.entity;

import com.google.common.collect.Lists;
import com.summer.commen.base.DataEntity;
import lombok.Data;

import java.util.List;

@Data
public class User extends DataEntity<User> {

    /**
     * 是否启用 启用 0
     */
    public static final String USER_STATUS_NORMAL = "0";
    /**
     * 是否启用 禁用 1
     */
    public static final String USER_STATUS_LOCK = "1";

    private String username; // 登录用户名
    private String password; // 密码
    private String name; // 用户姓名
    private String phone; // 手机号
    private String email; // 邮箱
    private String status; // 是否启用

    private List<Role> roleList = Lists.newArrayList();

    private List<Permission> permissionList = Lists.newArrayList();

    public User() {
        super();
        this.status = USER_STATUS_NORMAL;
    }

    public User(String id) {
        super(id);
        this.status = USER_STATUS_NORMAL;
    }
}
