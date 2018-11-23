package com.summer.modules.sys.web;

import com.alibaba.fastjson.JSON;
import com.summer.commen.base.AbstractBaseController;
import com.summer.commen.config.security.permission.MySecurityMetadataSource;
import com.summer.commen.constant.CommenConstant;
import com.summer.commen.utils.ResultJSON;
import com.summer.commen.utils.StringUtils;
import com.summer.modules.sys.entity.Permission;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @Author: Dashwood
 * @Date: 2018/7/28 21:31
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/api/sys/permission")
@Api(description = "系统权限接口")
public class PermissionController extends AbstractBaseController<PermissionService, Permission> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MySecurityMetadataSource mySecurityMetadataSource;
//    @ModelAttribute
//    public Permission getEntity(@RequestParam(required = false) String id) {
//        Permission entity = null;
//        if (StringUtils.isNotBlank(id)) {
//            entity = service.get(id);
//        }
//        if (entity == null) {
//            entity = new Permission();
//        }
//        return entity;
//    }

    @RequestMapping(value = "findMenuByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "通过登录者id，获取该用户的菜单及权限")
    public ResultJSON findMenuByUserId () {
        User user = securityUtils.getCurrUser();
        String key = String.format("userPermission::userMenuList:%s", user.getId());
        String menuString = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(menuString)) {
            return ResultJSON.setData(JSON.parseArray(menuString));
        }

        List<Permission> list = service.findMenuByUserId(user.getId());
        redisTemplate.opsForValue().set(key, JSON.toJSONString(list));
        return ResultJSON.setData(list);
    }

    @Override
    public ResultJSON save(Permission permission) {
        // 新增菜单权限，判断页面name名称不重复
        if (CommenConstant.PERMISSION_TYPE_PAGE.equals(permission.getType())) {
            Permission p = new Permission();
            p.setType(CommenConstant.PERMISSION_TYPE_PAGE);
            p.setName(permission.getName());
            List<Permission> list = service.findList(permission);
            if (list != null && list.size() > 0) {
                return ResultJSON.setErrorMsg("英文名称已存在");
            }
        }

        try {
            service.insert(permission);
        } catch (Exception e) {
            return ResultJSON.setErrorMsg("保存数据失败");
        }
        //重新加载权限
        mySecurityMetadataSource.loadResourceDefine();
        //手动删除缓存
        redisTemplate.delete("permission::allList");

        return ResultJSON.setOkMsg("保存数据成功");
    }

    @Override
    public ResultJSON update(Permission permission) {
        // 修改菜单权限，判断页面name名称不重复
        if (CommenConstant.PERMISSION_TYPE_PAGE.equals(permission.getType())) {
            Permission oldp = service.get(permission.getId());
            if (!oldp.getName().equals(permission.getName())) {
                Permission p = new Permission();
                p.setType(CommenConstant.PERMISSION_TYPE_PAGE);
                p.setName(permission.getName());
                List<Permission> list = service.findList(permission);
                if (list != null && list.size() > 0) {
                    return ResultJSON.setErrorMsg("英文名称已存在");
                }
            }
        }

        try {
            service.update(permission);
        } catch (Exception e) {
            return ResultJSON.setErrorMsg("修改数据失败");
        }
        //重新加载权限
        mySecurityMetadataSource.loadResourceDefine();
        //手动批量删除缓存
        Set<String> keys = redisTemplate.keys("userPermission:" + "*");
        redisTemplate.delete(keys);
        Set<String> keysUser = redisTemplate.keys("user:" + "*");
        redisTemplate.delete(keysUser);
        redisTemplate.delete("permission::allList");
        return ResultJSON.setOkMsg("修改数据成功");
    }

    @RequestMapping(value = "getMenuAllList", method = RequestMethod.GET)
    @ApiOperation(value = "获取全部菜单权限信息")
    public ResultJSON getMenuAllList() {
        return ResultJSON.setData(service.getAllList());
    }
}
