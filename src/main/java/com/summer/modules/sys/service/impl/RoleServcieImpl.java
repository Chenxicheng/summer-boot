package com.summer.modules.sys.service.impl;

import com.summer.commen.base.AbstractBaseService;
import com.summer.commen.utils.ResultJSON;
import com.summer.commen.utils.StringUtils;
import com.summer.modules.sys.dao.RoleDao;
import com.summer.modules.sys.entity.Permission;
import com.summer.modules.sys.entity.Role;
import com.summer.modules.sys.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;
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
    public ResultJSON validateName(String name) {
        if (StringUtils.isBlank(name)) {
            return ResultJSON.setErrorMsg(200, "请输入英文名称");
        }
        String pattern = "^(ROLE_[A-Z]+)|(ROLE_[A-Z]+[0-9]+)$";
//        if ("ROLE_".startsWith(name)) {
//            return ResultJSON.setErrorMsg(200, "英文名称以ROLE_开头");
//        }
//
//        if (StringUtils.isAllUpperCase(name)) {
//            return ResultJSON.setErrorMsg(200, "英文名称为大写");
//        }
        if (Pattern.matches(pattern, name)) {
            return ResultJSON.setErrorMsg(200, "英文名称以ROLE_为首并以大写字母或加数字组成");
        }

        List<Role> list = dao.findList(new Role(name, null));
        if (list != null && list.size() > 0) {
            return ResultJSON.setErrorMsg(200, "英文名称已存在");
        }
        return ResultJSON.setOkMsg("英文名称未添加");
    }

    @Override
    public void deleteByLogic(String id) {

    }
}
