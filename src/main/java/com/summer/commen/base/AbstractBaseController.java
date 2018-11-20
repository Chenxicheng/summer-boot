package com.summer.commen.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.base.Splitter;
import com.summer.commen.utils.ResultJSON;
import com.summer.commen.utils.StringUtils;
import com.summer.modules.sys.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class AbstractBaseController <S extends BaseService<T>, T> {

    @Autowired
    protected S service;
    @Autowired
    protected SecurityUtils securityUtils;

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    @ResponseBody
    public ResultJSON get(@PathVariable String id) {
        T entity = service.get(id);
        return ResultJSON.setData(entity);
    }

    @RequestMapping(value = "findPage", method = RequestMethod.GET)
    @ApiOperation(value = "分页查询数据")
    @ResponseBody
    public ResultJSON findPage(@ModelAttribute T entity, int pageNo, int pageSize) {
        Page<T> page = service.findPage(new Page<T>(pageNo, pageSize), entity);
        return ResultJSON.setData(page);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ApiOperation(value = "新增插入数据")
    @ResponseBody
    public ResultJSON save(T entity) {
        try {
            service.insert(entity);
        } catch (Exception e) {
            return ResultJSON.setErrorMsg("保存数据失败");
        }
        return ResultJSON.setOkMsg("保存数据成功");
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    @ApiOperation(value = "修改数据")
    @ResponseBody
    public ResultJSON update(T entity) {
        try {
            service.update(entity);
        } catch (Exception e) {
            return ResultJSON.setErrorMsg("修改数据失败");
        }
        return ResultJSON.setOkMsg("修改数据成功");
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "通过id删除数据")
    @ResponseBody
    public ResultJSON delete(@PathVariable String id) {
        service.delete(id);
        return ResultJSON.setOkMsg("删除数据成功");
    }

    @RequestMapping(value = "deleteAll", method = RequestMethod.DELETE)
    @ApiOperation(value = "批量删除数据")
    @ResponseBody
    public ResultJSON deleteAll (String ids) {
        List<String> idList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(ids);
        service.deleteAll(idList);
        return ResultJSON.setOkMsg("批量删除数据成功");
    }

}
