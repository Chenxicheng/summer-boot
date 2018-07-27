package com.summer.commen.base;

import com.google.common.collect.Lists;
import com.summer.modules.sys.entity.Permission;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Dashwood
 * @Date: 2018/7/21 14:06
 * @Version: 1.0
 */
@Data
public class TreeEntity<T> extends DataEntity<T> {

    protected String parentId;
    protected String parentIds;

    protected List<T> children = Lists.newArrayList();

    public TreeEntity() {
        super();
    }

    public TreeEntity(String parentId) {
        this.parentId = parentId;
    }
}
