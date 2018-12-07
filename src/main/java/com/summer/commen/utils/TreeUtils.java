package com.summer.commen.utils;

import com.google.common.collect.Lists;
import com.summer.commen.base.TreeEntity;
import com.summer.commen.constant.CommenConstant;
import com.summer.modules.sys.entity.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 将记录数据转为树形list
 * @Author: Dashwood
 */
public class TreeUtils {

    /**
     * 格式化list为树形list
     * @param list
     * @return
     */
    public static <T extends TreeEntity<T>> List<T> formatTree(List<T> list) {
        List<T> nodeList = new ArrayList<T>();
        for(T node1 : list){
            boolean mark = false;
            for(T node2 : list){
                if(node1.getParentId()!=null && node1.getParentId().equals(node2.getId())){
                    mark = true;
                    if(node2.getChildren() == null) {
                        node2.setChildren(new ArrayList<T>());
                    }
                    node2.getChildren().add(node1);
                    break;
                }
            }
            if(!mark){
                nodeList.add(node1);
            }
        }
        return nodeList;
    }

    /**
     * 格式化菜单组件
     * @param list
     * @return
     */
    public static  List<Permission> formatMenuTree(List<Permission> list) {
        List<Permission> nodeList = new ArrayList<Permission>();
        for(Permission node1 : list){
            boolean mark = false;
            for(Permission node2 : list){
                if(node1.getParentId()!=null && node1.getParentId().equals(node2.getId())){
                    mark = true;
                    if(node2.getChildren() == null) {
                        node2.setChildren(Lists.newArrayList());
                    }
                    if (node2.getButtonChildren() == null) {
                        node2.setButtonChildren(Lists.newArrayList());
                    }
                    if (CommenConstant.PERMISSION_TYPE_PAGE.equals(node1.getType())) {
                        node2.getChildren().add(node1);
                    } else {
                        node2.getButtonChildren().add(node1);
                    }
                    break;
                }
            }
            if(!mark){
                nodeList.add(node1);
            }
        }
        return nodeList;
    }


}
