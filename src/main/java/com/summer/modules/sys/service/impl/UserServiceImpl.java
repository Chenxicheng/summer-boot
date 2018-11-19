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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 系统用户业务层
 */
@Service
@CacheConfig(cacheNames = "user")
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

    @Override
    public void deleteByLogic(String id) {

    }

    /**
     * 加密密码
     * @param password
     * @return
     */
    private static String encryptPassword (String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 缓存用户信息
     * @param username
     * @return
     */
    @Override
    @Cacheable(key="#username")
    public User findByUsername(String username) {
        List<User> userList = dao.findListByUsername(username);
        if (userList != null && userList.size() == 1) {
            User user = userList.get(0);
            List<Role> roleList = roleDao.findRoleListByUserId(user.getId());
            if (roleList != null && roleList.size() > 0) {
                user.setRoleList(roleList);
            }
            List<Permission> permissionList = permissionDao.findPromissionsByUserId(user.getId());
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
        user.preUpdate(securityUtils.getCurrUser());
        dao.updatePasswordById(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateUserInfo(User user) {
        user.preUpdate(securityUtils.getCurrUser());
        dao.updateUserInfo(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateByIsEnable(User user) {
        user.preUpdate(securityUtils.getCurrUser());
        dao.updateByStatus(user);
    }


    @Override
    @Cacheable(key = "'username:'+#username")
    public User getByUsername(String username) {
        User user = dao.getByUsername(username);
        return user;
    }


}
