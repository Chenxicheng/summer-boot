package com.summer.modules.sys.entity;

import com.google.common.collect.Lists;
import com.summer.commen.base.DataEntity;
import lombok.Data;

import java.util.List;

@Data
public class Role extends DataEntity<Role>{

    private String name; // 英文名称

    private String cnName; // 中文名称

    private List<Permission> permissionList = Lists.newArrayList(); // 权限集合

    public Role(String name, String cnName) {
        super();
        this.name = name;
        this.cnName = cnName;
    }

    public Role() {
        super();
    }

    public Role(String id) {
        super(id);
    }
}
