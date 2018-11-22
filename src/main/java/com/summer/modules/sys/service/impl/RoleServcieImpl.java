package com.summer.modules.sys.service.impl;

import com.summer.commen.base.AbstractBaseService;
import com.summer.modules.sys.dao.RoleDao;
import com.summer.modules.sys.entity.Permission;
import com.summer.modules.sys.entity.Role;
import com.summer.modules.sys.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RoleServcieImpl extends AbstractBaseService<RoleDao, Role> implements RoleService {
    @Override
    @Transactional(readOnly = false)
    public void accessPermission(Role role, List<String> permissionIdList) {
        role.setPermissionList(permissionIdList
                                            .stream()
                                            .map(pid -> {
                                                return new Permission(pid);
                                            }).collect(Collectors.toList()));
        dao.deletePermissionRole(role);
        dao.insertPermissionRole(role);
    }

    @Override
    public void deleteByLogic(String id) {

    }
}
