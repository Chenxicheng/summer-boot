package com.summer.modules.sys.entity;

import com.google.common.collect.Lists;
import com.summer.commen.base.TreeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单权限类
 * @Author: Dashwood
 * @Date: 2018/7/21 14:13
 * @Version: 1.0
 */
@Data
public class Permission extends TreeEntity<Permission>{

    @ApiModelProperty(value = "菜单/权限名称")
    private String name;
    @ApiModelProperty(value = "中文名称")
    private String title;
    @ApiModelProperty(value = "图表")
    private String icon;
    @ApiModelProperty(value = "vue组件地址")
    private String component;
    @ApiModelProperty(value = "类型 0-菜单 1-按钮")
    private String type;
    @ApiModelProperty(value = "页面路径/资源链接url")
    private String path;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "层级")
    private String level;
    @ApiModelProperty(value = "按钮权限类型")
    private String buttonType;
    @ApiModelProperty(value = "是否启用 0启用 -1禁用")
    private String status;
    @ApiModelProperty(value = "按钮集合")
    private List<Permission> buttonChildren = Lists.newArrayList();


    private String roleNames;

    public Permission() {
        super();
    }

    public Permission(String id) {
        super(id);
    }
}
