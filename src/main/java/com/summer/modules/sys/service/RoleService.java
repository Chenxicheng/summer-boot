package com.summer.modules.sys.service;

import com.summer.commen.base.BaseService;
import com.summer.commen.utils.ResultJSON;
import com.summer.modules.sys.entity.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {

    /**
     * 设置权限
     * @param role
     * @param permissionIdList
     */
    void accessPermission (Role role, List<String> permissionIdList);

    ResultJSON validateName(String name);
}
