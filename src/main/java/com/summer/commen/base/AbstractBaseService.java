package com.summer.commen.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.summer.commen.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract class AbstractBaseService<D extends CrudDao<T>, T extends DataEntity<T>> {

    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) {
        return get(entity.getId());
    }

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询分页数据
     * @param page 分页对象
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        page.setRecords(dao.findList(page, entity));
        return page;
    }

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    @Transactional(readOnly = false)
    public void save(T entity) {
        if (StringUtils.isBlank(entity.getId())){
//            entity.preInsert();
            insert(entity);
        }else{
//            entity.preUpdate();
            update(entity);
        }
    }

    @Transactional(readOnly = false)
    public void insert(T entity) {
        entity.preInsert();
        dao.insert(entity);
    }

    @Transactional(readOnly = false)
    public void update(T entity) {
        entity.preUpdate();
        dao.update(entity);
    }

    /**
     * 删除数据
     * @param entity
     */
    @Transactional(readOnly = false)
    public void delete(T entity) {
        dao.delete(entity);
    }


    /**
     * 逻辑删除
     * @param entity
     */
    @Transactional(readOnly = false)
    public void deleteByLogic (T entity) {
        dao.deleteByLogic(entity);
    }


    /**
     * 删除全部数据
     * @param entitys
     */
    @Transactional(readOnly = false)
    public void deleteAll(List<T> entitys) {
        for (T entity : entitys) {
            dao.delete(entity);
        }
    }

}
