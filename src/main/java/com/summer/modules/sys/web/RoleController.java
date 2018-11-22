package com.summer.modules.sys.web;

import com.summer.commen.base.AbstractBaseController;
import com.summer.commen.utils.ResultJSON;
import com.summer.modules.sys.entity.Role;
import com.summer.modules.sys.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/sys/role")
@Api(description = "系统角色接口")
public class RoleController extends AbstractBaseController<RoleService, Role> {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ResultJSON update(Role role) {
        try {
            service.update(role);
        } catch (Exception e) {
            return ResultJSON.setErrorMsg("修改数据失败");
        }
        //手动批量删除缓存
        Set<String> keysUser = redisTemplate.keys("user:" + "*");
        redisTemplate.delete(keysUser);
        return ResultJSON.setOkMsg("修改数据成功");
    }

}
