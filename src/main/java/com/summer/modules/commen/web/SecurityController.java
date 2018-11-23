package com.summer.modules.commen.web;

import com.summer.commen.utils.ResultJSON;
import com.summer.commen.utils.StringUtils;
import com.summer.modules.sys.entity.User;
import com.summer.modules.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Dashwood
 *
 */
@RestController
@Api(description = "Security相关接口")
@RequestMapping("/api/commen/security")
public class SecurityController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    @ApiOperation(value = "没有登录")
    public ResultJSON needLogin(){
        return ResultJSON.setErrorMsg(HttpServletResponse.SC_UNAUTHORIZED, "您还未登录");
    }

    @RequestMapping(value = "verifyUsername",  method = RequestMethod.GET)
    @ApiOperation(value = "校验用户名是否重复")
    public ResultJSON verifyUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return ResultJSON.setErrorMsg("用户名不能为空");
        }
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResultJSON.setOkMsg("该用户名未添加");
        }
        return ResultJSON.setErrorMsg(200, "该用户名已存在");
    }

}
