package com.summer.commen.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈喜骋
 * @date 20170828
 */
public class ResultJSON extends JSONObject implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 构造器
     */
    public ResultJSON() {

    }

    /**
     * 传参构造器
     * @param status 状态值 true 正确 false 错误
     */
    public ResultJSON(boolean status) {
        put("status", status);
    }

    private static ResultJSON error() {
        return new ResultJSON(false);
    }

    /**
     * 错误信息
     * @param message
     * @return {status: false, code: 500, message: message}
     */
    public static ResultJSON setErrorMsg(String message) {
        return error().put("message", message).put("code", 500);
    }

    /**
     * 错误信息
     * @param code
     * @param message
     * @return {status: false, code: code, message: message}
     */
    public static ResultJSON setErrorMsg(Integer code, String message) {

        return error().put("code", code).put("message", message);
    }


    private static ResultJSON ok() {
        return new ResultJSON(true);
    }

    /**
     * 成功信息
     * @return {status: true, code: 200, message: "success"}
     */
    public static ResultJSON setOkMsg() {
        return ok().put("code", 200).put("message", "success");
    }
    /**
     * 成功信息
     * @param message 信息
     * @return {status: true, code: 200, message: message}
     */
    public static ResultJSON setOkMsg(String message) {
        return ok().put("code", 200).put("message", message);
    }

    /**
     * 成功并设置返回值
     * @param object
     * @param message
     * @return
     */
    public static ResultJSON setData(Object object, String message) {
        return ok().put("code", 200).put("message", message).put("result", object);
    }

    /**
     * 成功并设置返回值
     * @param object
     * @return
     */
    public static ResultJSON setData(Object object) {
        return ok().put("code", 200).put("message", "success").put("result", object);
    }

    /**
     * 设置参数
     * @param key 键
     * @param object 值
     * @return
     */
    public ResultJSON put(String key, Object object) {
        super.put(key, object);
        return this;
    }


    public static void main(String[] args) {
        System.out.println(ResultJSON.setOkMsg().put("token", "fdsfdsafdsafdsfa"));
        System.out.println(ResultJSON.setErrorMsg("1234"));
    }

}
