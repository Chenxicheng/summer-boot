package com.summer.commen.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Dashwood
 * @Date: 2018/7/21 14:06
 * @Version: 1.0
 */
@Data
public class TreeEntity<T> extends DataEntity<T> {

    protected String parentId;
    protected String parentIds;

    public TreeEntity() {
        super();
    }

    public TreeEntity(String parentId) {
        this.parentId = parentId;
    }
}
