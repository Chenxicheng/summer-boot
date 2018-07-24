package com.summer.commen.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Splitter;
import com.summer.commen.utils.ResultJSON;
import com.summer.commen.utils.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class AbstractBaseController <S extends BaseService<T>, T extends DataEntity<T>> {

    @Autowired
    protected S service;

    @ModelAttribute
    public T getEntity(@RequestParam(required = false) String id) {
        T entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = service.get(id);
        }
        if (entity == null) {
            entity = (T)new Object();
        }
        return entity;
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    @ResponseBody
    public ResultJSON get(@PathVariable String id) {
        T entity = service.get(id);
        return ResultJSON.ok(HttpServletResponse.SC_OK, "success").put("entity", entity);
    }

    @RequestMapping(value = "findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询数据")
    @ResponseBody
    public ResultJSON findPage(T entity, int pageNo, int pageSize) {
        Page<T> page = service.findPage(new Page<T>(pageNo, pageSize), entity);
        return ResultJSON.ok(HttpServletResponse.SC_OK, "success").put("list", page.getRecords()).put("count", page.getTotal());
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改数据")
    @ResponseBody
    public ResultJSON save(T entity) {
        service.save(entity);
        return ResultJSON.ok(HttpServletResponse.SC_OK, "保存数据成功");
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "通过id删除数据")
    @ResponseBody
    public ResultJSON delete(T entity) {
        service.delete(entity);
        return ResultJSON.ok(HttpServletResponse.SC_OK, "删除数据成功");
    }

    @RequestMapping(value = "deleteAll", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除数据")
    @ResponseBody
    public ResultJSON deleteAll (String ids) {
        List<String> idList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(ids);
        service.deleteAll(idList);
        return ResultJSON.ok(HttpServletResponse.SC_OK, "批量删除数据成功");
    }

}
