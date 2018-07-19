package com.summer.modules.sys.entity;

import com.summer.commen.base.DataEntity;
import lombok.Data;

@Data
public class Role extends DataEntity<Role>{

    private String name; // 英文名称

    private String cnName; // 中文名称

    public Role(String name, String cnName) {
        super();
        this.name = name;
        this.cnName = cnName;
    }

    public Role() {
        super();
    }
}
