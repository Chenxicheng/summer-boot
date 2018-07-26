package com.summer.modules.sys.web;

import com.summer.commen.base.AbstractBaseController;
import com.summer.commen.utils.ResultJSON;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sys/user")
@Api(description = "系统用户接口")
public class UserController extends AbstractBaseController<UserService, User>{

    @RequestMapping(value = "getUserInfo",  method = RequestMethod.GET)
    @ApiOperation(value = "获取当前登陆用户者信息")
    public ResultJSON getUserInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = service.getByUsername(userDetails.getUsername());
        return ResultJSON.ok(200, "success").put("user", user);
    }

}