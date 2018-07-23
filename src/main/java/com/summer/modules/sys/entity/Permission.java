package com.summer.modules.sys.entity;

import com.summer.commen.base.DataEntity;
import lombok.Data;

/**
 * @Author: Dashwood
 * @Date: 2018/7/21 14:13
 * @Version: 1.0
 */
@Data
public class Permission extends DataEntity<Permission> {

    private String menuId;
    private String name;
    private String type;
    private String url;
    private String title;
    private Integer sort;
    private String status;

    public Permission() {
        super();
    }

    public Permission(String id) {
        super(id);
    }
}
