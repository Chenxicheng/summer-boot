package com.summer.modules.sys.service.impl;

import com.summer.commen.base.AbstractBaseService;
import com.summer.modules.sys.dao.RoleDao;
import com.summer.modules.sys.entity.Role;
import com.summer.modules.sys.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RoleServcieImpl extends AbstractBaseService<RoleDao, Role> implements RoleService {
}
