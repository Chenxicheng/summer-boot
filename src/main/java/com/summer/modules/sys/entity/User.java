package com.summer.modules.sys.entity;

import com.google.common.collect.Lists;
import com.summer.commen.base.DataEntity;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "用户名")
    private String username; // 登录用户名
    @ApiModelProperty(value = "密码")
    private String password; // 密码
    @ApiModelProperty(value = "姓名")
    private String name; // 用户姓名
    @ApiModelProperty(value = "手机号")
    private String phone; // 手机号
    @ApiModelProperty(value = "邮箱")
    private String email; // 邮箱
    @ApiModelProperty(value = "状态 默认0正常 1禁用")
    private String status; // 是否启用
    @ApiModelProperty(value = "拥有角色集合")
    private List<Role> roleList = Lists.newArrayList();
    @ApiModelProperty(value = "拥有权限集合")
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
