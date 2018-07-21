package com.summer.modules.sys.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Dashwood
 * @Date: 2018/7/21 21:15
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping("hello")
    public String hello(String say) {
        return say;
    }

    @RequestMapping("bye")
    public String bye() {
        return "bye";
    }

    @RequestMapping("test")
    public String test() {
        return "test";
    }

}
