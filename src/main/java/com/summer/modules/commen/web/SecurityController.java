package com.summer.modules.commen.web;

import com.summer.commen.utils.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    @ApiOperation(value = "没有登录")
    public ResultJSON needLogin(){
        return ResultJSON.setErrorMsg(HttpServletResponse.SC_UNAUTHORIZED, "您还未登录");
    }

}
