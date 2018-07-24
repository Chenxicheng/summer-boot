package com.summer.modules.sys.dao;

import com.summer.commen.base.CrudDao;
import com.summer.modules.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao extends CrudDao<User> {
    /**
     * 根据用户名获取用户信息
     * @param user
     * @return
     */
    User getByUsername(String username);

    List<User> findListByUsername(String username);

    /**
     * 根据用户id，删除用户角色关系表中数据
     * @param user
     */
    void deleteUserRole(User user);

    /**
     * 向用户角色关系表插入数据
     * @param user
     */
    void insertUserRole(User user);

    /**
     * 更新密码
     * @param user
     */
    void updatePasswordById(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUserInfo(User user);

    /**
     * 更新是否启用状态
     * @param user
     */
    void updateByStatus(User user);



}
