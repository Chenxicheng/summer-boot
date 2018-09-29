package com.summer.modules.sys.web;

import com.summer.commen.base.AbstractBaseController;
import com.summer.commen.utils.ResultJSON;
import com.summer.commen.utils.StringUtils;
import com.summer.modules.sys.entity.Permission;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Dashwood
 * @Date: 2018/7/28 21:31
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/api/sys/permission")
@Api(description = "系统权限接口")
public class PermissionController extends AbstractBaseController<PermissionService, Permission> {

    @ModelAttribute
    public Permission getEntity(@RequestParam(required = false) String id) {
        Permission entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = service.get(id);
        }
        if (entity == null) {
            entity = new Permission();
        }
        return entity;
    }

    @RequestMapping(value = "findMenuByUserId/{userId}", method = RequestMethod.GET)
    @ApiOperation(value = "通过登录者id，获取该用户的菜单及权限")
    public ResultJSON findMenuByUserId (@PathVariable String userId) {
        List<Permission> list = service.findMenuByUserId(userId);
        return ResultJSON.ok(200, "ok").put("menuList", list);
    }

}
