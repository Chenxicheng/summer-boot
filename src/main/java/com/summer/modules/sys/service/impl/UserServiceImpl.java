package com.summer.modules.sys.service.impl;

import com.summer.commen.base.AbstractBaseService;
import com.summer.commen.utils.StringUtils;
import com.summer.modules.sys.dao.PermissionDao;
import com.summer.modules.sys.dao.RoleDao;
import com.summer.modules.sys.dao.UserDao;
import com.summer.modules.sys.entity.Permission;
import com.summer.modules.sys.entity.Role;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户业务层
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractBaseService<UserDao, User> implements UserService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User get(String id) {
        User user = super.get(id);
        if (user != null && StringUtils.isNotBlank(user.getId())) {
            List<Role> roleList = roleDao.findRoleListByUserId(user.getId());
            if (roleList != null && roleList.size() > 0) {
                user.setRoleList(roleList);
            }

        }
        return user;
    }

    @Override
    @Transactional(readOnly = false)
    public void insert(User user) {
        user.setPassword(encryptPassword(user.getPassword()));
        super.insert(user);
        if (user.getRoleList().size() > 0) {
            dao.insertUserRole(user);
        }

    }

    @Override
    @Transactional(readOnly = false)
    public void update(User user) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(encryptPassword(user.getPassword()));
        }
        super.update(user);
        dao.deleteUserRole(user);
        if (user.getRoleList().size() > 0) {
            dao.insertUserRole(user);
        }
    }

    /**
     * 加密密码
     * @param password
     * @return
     */
    private static String encryptPassword (String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public User getByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        user = dao.getByUsername(user);
        if (user != null && StringUtils.isNotBlank(user.getId())) {
            List<Role> roleList = roleDao.findRoleListByUserId(user.getId());
            if (roleList != null && roleList.size() > 0) {
                user.setRoleList(roleList);
            }
            List<Permission> permissionList = permissionDao.findListByUserId(user.getId());
            if (permissionList != null && permissionList.size() > 0) {
                user.setPermissionList(permissionList);
            }
            return user;
        }
        return null;
    }


    @Override
    @Transactional(readOnly = false)
    public void updatePasswordById(User user) {
        user.setPassword(encryptPassword(user.getPassword()));
        user.preUpdate();
        dao.updatePasswordById(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUserInfo(User user) {
        user.preUpdate();
        dao.updateUserInfo(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateByIsEnable(User user) {
        user.preUpdate();
        dao.updateByStatus(user);
    }


}
