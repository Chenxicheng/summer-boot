package com.summer.modules.sys.web;

import com.summer.commen.base.AbstractBaseController;
import com.summer.commen.utils.ResultJSON;
import com.summer.modules.sys.utils.SecurityUtils;
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
    public ResultJSON save(User user) {
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

    @RequestMapping(value = "getUserInfo",  method = RequestMethod.GET)
    @ApiOperation(value = "获取当前登陆用户者信息")
    public ResultJSON getUserInfo() {
        User user = securityUtils.getCurrUser();
        user.setPassword(null);
        return ResultJSON.setData(user);
    }


}
