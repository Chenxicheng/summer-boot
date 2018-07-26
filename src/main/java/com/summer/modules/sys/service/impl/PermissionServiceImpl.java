package com.summer.modules.sys.service.impl;

import com.summer.commen.base.AbstractBaseService;
import com.summer.modules.sys.dao.PermissionDao;
import com.summer.modules.sys.entity.Permission;
import com.summer.modules.sys.service.PermissionService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Dashwood
 * @Date: 2018/7/21 15:01
 * @Version: 1.0
 */
@Service
@CacheConfig(cacheNames = "permission")
@Transactional(readOnly = true)
public class PermissionServiceImpl extends AbstractBaseService<PermissionDao, Permission> implements PermissionService {

    @Override
    @Cacheable(key = "'menuList:'+#userId")
    public List<Permission> findMenuByUserId(String userId) {
        List<Permission> permissionList = dao.findListByUserId(userId);

        return null;
    }
}
