package com.summer.modules.sys.entity;

import com.google.common.collect.Lists;
import com.summer.commen.base.DataEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class Role extends DataEntity<Role>{

    public static final String ROLE_ADMIN_ID = "1";

    @ApiModelProperty(value = "角色英文名称 以ROLE_开头")
    private String name; // 英文名称
    @ApiModelProperty(value = "角色中文名称")
    private String cnName; // 中文名称
    @ApiModelProperty(value = "角色下的权限集合")
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
