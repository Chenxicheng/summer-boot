package com.summer.modules.sys.web;

import com.summer.commen.base.AbstractBaseController;
import com.summer.commen.utils.ResultJSON;
import com.summer.commen.utils.SecurityUtils;
import com.summer.commen.utils.StringUtils;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sys/user")
@Api(description = "系统用户接口")
public class UserController extends AbstractBaseController<UserService, User>{

    @Autowired
    private SecurityUtils securityUtils;

//    @ModelAttribute
//    public User getEntity(@RequestParam(required = false) String id) {
//        User entity = null;
//        if (StringUtils.isNotBlank(id)) {
//            entity = service.get(id);
//        }
//        if (entity == null) {
//            entity = new User();
//        }
//        return entity;
//    }

    @RequestMapping(value = "getUserInfo",  method = RequestMethod.GET)
    @ApiOperation(value = "获取当前登陆用户者信息")
    public ResultJSON getUserInfo() {
        User user = securityUtils.getCurrUser();
        user.setPassword(null);
        return ResultJSON.ok("success").put("user", user);
    }

    @RequestMapping(value = "verifyUsername",  method = RequestMethod.GET)
    @ApiOperation(value = "校验用户名是否重复")
    public ResultJSON verifyUsername(String username) {
        return ResultJSON.ok("success");
    }
}
