package com.summer.modules.sys.entity;

import com.summer.commen.base.TreeEntity;
import lombok.Data;

/**
 * @Author: Dashwood
 * @Date: 2018/7/21 14:13
 * @Version: 1.0
 */
@Data
public class Permission extends TreeEntity<Permission>{

    private String name;
    private String url;
    private String method;
    private Integer sort;

    public Permission() {
        super();
    }

    public Permission(String id) {
        super(id);
    }
}
