package com.summer.modules.sys.service;

import com.summer.commen.base.BaseService;
import com.summer.modules.sys.entity.User;

public interface UserService extends BaseService<User> {
    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 更新密码
     * @param user
     */
    void updatePassword(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUserInfo(User user);

    /**
     * 更新是否启用状态
     * @param user
     */
    void updateByIsEnable(User user);

    /**
     * 根据根据用户名获取用户信息
     * 缓存数据
     * @param username
     * @return
     */
    User getByUsername (String username);
}
