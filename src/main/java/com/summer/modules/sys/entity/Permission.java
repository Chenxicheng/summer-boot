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
    private String title;
    private String icon;
    private String component;
    private String type;
    private String path;
    private Integer sort;
    private String level;
    private String buttonType;
    private String status;

    private String roleNames;

    public Permission() {
        super();
    }

    public Permission(String id) {
        super(id);
    }
}
