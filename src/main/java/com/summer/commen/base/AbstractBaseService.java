package com.summer.commen.base;

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
        return dao.get(entity);
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
//    public Page<T> findPage(Page<T> page, T entity) {
//        entity.setPage(page);
//        page.setList(dao.findList(entity));
//        return page;
//    }

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    @Transactional(readOnly = false)
    public void save(T entity) {
        if (StringUtils.isBlank(entity.getId())){
//            entity.preInsert();
            dao.insert(entity);
        }else{
//            entity.preUpdate();
            dao.update(entity);
        }
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
     * 删除数据
     * @param id
     */
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
     * 逻辑删除
     * @param id
     */
    @Transactional(readOnly = false)
    public void deleteByLogic (String id) {
        dao.deleteByLogic(id);
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
