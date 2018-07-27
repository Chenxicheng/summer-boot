package com.summer.commen.utils;

import com.summer.commen.base.TreeEntity;

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


}
