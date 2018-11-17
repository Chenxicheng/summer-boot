package com.summer.commen.base;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public interface BaseService<T> {

    T get(String id);

    Page<T> findPage(Page<T> page, T t);

    List<T> findList(T t);

    void insert (T t);

    void update (T t);

    void delete (T t);

    void delete (String id);

    void deleteAll (List<String> idList);

    void deleteByLogic (T t);

    void deleteByLogic (String id);
}
