package com.summer.commen.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.summer.commen.utils.SecurityUtils;
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
     * 新增插入数据
     * @param entity
     */
    @Transactional(readOnly = false)
    public void insert(T entity) {
        entity.preInsert();
        dao.insert(entity);
    }

    /**
     * 修改编辑数据
     * @param entity
     */
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

    @Transactional(readOnly = false)
    public void delete(String id) {
        dao.delete(id);
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
     * @param idList
     */
    @Transactional(readOnly = false)
    public void deleteAll(List<String> idList) {
        for (String id : idList) {
            dao.delete(id);
        }
    }

}
