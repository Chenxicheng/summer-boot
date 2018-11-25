package com.summer.modules.sys.web;

import com.summer.commen.base.AbstractBaseController;
import com.summer.commen.utils.ResultJSON;
import com.summer.commen.utils.StringUtils;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sys/user")
@Api(description = "系统用户接口")
public class UserController extends AbstractBaseController<UserService, User>{
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
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResultJSON save(@ModelAttribute User user) {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return ResultJSON.setErrorMsg("用户名或密码不能为空");
        }
        User u = service.findByUsername(user.getUsername());
        if ( u != null) {
            return ResultJSON.setErrorMsg("用户名已存在");
        }
        redisTemplate.delete("user::"+user.getUsername());
        try {
            service.insert(user);
        } catch (Exception e) {
            return ResultJSON.setErrorMsg("添加用户失败");
        }
        return ResultJSON.setOkMsg(String.format("添加用户 %s 成功", user.getUsername()));
    }

    @RequestMapping(value = "editUserInfo",  method = RequestMethod.PUT)
    @ApiOperation(value = "编辑用户基本信息，不编辑用户名及密码")
    public ResultJSON editUserInfo(User user) {
        User oldUser = service.get(user.getId());
        user.setUsername(oldUser.getUsername());
        user.setPassword(oldUser.getPassword());
        try {
            service.updateUserInfo(user);
        } catch (Exception e) {
            ResultJSON.setErrorMsg("修改用户信息失败");
        }
        return ResultJSON.setOkMsg("修改用户信息成功");
    }

    @RequestMapping(value = "updatePassword",  method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户密码")
    public ResultJSON updatePassword(User user) {
        try {
            service.updateUserInfo(user);
        } catch (Exception e) {
            ResultJSON.setErrorMsg("修改密码失败");
        }
        return ResultJSON.setOkMsg("修改密码成功");
    }

    @RequestMapping(value = "getUserInfo",  method = RequestMethod.GET)
    @ApiOperation(value = "获取当前登陆用户者信息")
    public ResultJSON getUserInfo() {
        User user = securityUtils.getCurrUser();
        user.setPassword(null);
        return ResultJSON.setData(user);
    }


}
