package com.summer.modules.sys.service;

import com.summer.commen.base.BaseService;
import com.summer.modules.sys.entity.Permission;

import java.util.List;

/**
 * @Author: Dashwood
 * @Date: 2018/7/21 15:01
 * @Version: 1.0
 */
public interface PermissionService extends BaseService<Permission>{

    /**
     * 根据用户id获取该用户所拥有权限
     * @param userId
     * @return
     */
    List<Permission> findMenuByUserId (String userId);

}
