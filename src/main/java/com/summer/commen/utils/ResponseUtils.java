package com.summer.commen.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Response 返回json 工具类
 * @Author: 陈喜骋
 * @Date: 2018/7/21 11:32
 * @Version: 1.0
 */
@Slf4j
public class ResponseUtils {

    /**
     * 返回json
     * @param response
     * @param resultJSON
     */
    public static void out(ServletResponse response, JSONObject resultJSON) {
        PrintWriter out = null;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
            out.println(resultJSON.toJSONString());
        } catch (IOException e) {
            log.error(e + "Response输出JSON出错");
        } finally {
            if(out!=null){
                out.flush();
                out.close();
            }
        }

    }



}
