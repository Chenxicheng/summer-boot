package com.summer.modules.sys.service.impl;

import com.google.common.collect.Lists;
import com.summer.commen.base.AbstractBaseService;
import com.summer.commen.constant.CommenConstant;
import com.summer.commen.utils.StringUtils;
import com.summer.commen.utils.TreeUtils;
import com.summer.modules.sys.dao.PermissionDao;
import com.summer.modules.sys.dao.RoleDao;
import com.summer.modules.sys.entity.Permission;
import com.summer.modules.sys.entity.Role;
import com.summer.modules.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @Author: Dashwood
 * @Date: 2018/7/21 15:01
 * @Version: 1.0
 */
@Service
@CacheConfig(cacheNames = "permission")
@Transactional(readOnly = true)
public class PermissionServiceImpl extends AbstractBaseService<PermissionDao, Permission> implements PermissionService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RoleDao roleDao;

    @Override
    @Cacheable(key = "'userMenuList:'+#userId")
    public List<Permission> findMenuByUserId(String userId) {
        List<Permission> permissionList = dao.findMenuListByUserId(userId);
        return TreeUtils.formatTree(permissionList);
    }

    @Override
    @Cacheable(key = "'allList'")
    public List<Permission> getAllList() {
        return TreeUtils.formatTree(dao.findAllList(new Permission()));
    }


    @Transactional(readOnly = false)
    @CacheEvict(key = "'menuList'")
    public void insert(Permission permission) {
        if (StringUtils.isBlank(permission.getParentIds())) {
            permission.setParentIds(permission.getParentId());
        } else {
            permission.setParentIds(permission.getParentIds()+","+permission.getParentId());
        }
        super.insert(permission);
        /**
         * 每次新建权限，自动给超级管理员添加对应权限
         */
        Role role = new Role(Role.ROLE_ADMIN_ID);
        role.setPermissionList(Lists.newArrayList(permission));
        roleDao.insertPermissionRole(role);
        //手动删除缓存
        stringRedisTemplate.delete("permission::allList");
    }

    @Transactional(readOnly = false)
    public void update (Permission permission) {
        super.update(permission);
        //手动批量删除缓存
        Set<String> keys = stringRedisTemplate.keys("userPermission:" + "*");
        stringRedisTemplate.delete(keys);
        Set<String> keysUser = stringRedisTemplate.keys("user:" + "*");
        stringRedisTemplate.delete(keysUser);
        Set<String> keysUserMenu = stringRedisTemplate.keys("permission::userMenuList:*");
        stringRedisTemplate.delete(keysUserMenu);
        stringRedisTemplate.delete("permission::allList");
    }

    @Override
    public void deleteByLogic(String id) {

    }

}
