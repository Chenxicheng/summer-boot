package com.summer.modules.sys.dao;

import com.summer.commen.base.CrudDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.summer.modules.sys.entity.Permission;

@Mapper
public interface PermissionDao extends CrudDao<Permission>{
    List<Permission> findListByUserId (String userId);
}
