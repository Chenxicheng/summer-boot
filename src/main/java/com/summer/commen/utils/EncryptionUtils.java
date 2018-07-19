package com.summer.commen.utils;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.Charset;

/**
 * 常用  编码/解码  工具类
 */
public class EncryptionUtils {

    /**
     * Base64加密
     * @param data String
     * @return String
     */
    public static String base64Encode(String data) {

        return Base64.encodeBase64String(data.getBytes());
    }

    /**
     * Base64加密
     * @param bytes byte[]
     * @return String
     */
    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * Base64加密
     * UTF-8编码
     * @param data String
     * @return String
     */
    public static String base64EncodeWithUTF8(String data) {

        return Base64.encodeBase64String(data.getBytes(Charset.forName("UTF-8")));
    }

    /**
     * Base64解密
     * @param data String
     * @return byte[]
     */
    public static byte[] base64Decode(String data) {
        return Base64.decodeBase64(data.getBytes());
    }

    /**
     * Base64解密
     * UTF-8编码
     * @param data String
     * @return byte[]
     */
    public static String base64DecodeWithUTF8(String data) {
        byte[] bytes = Base64.decodeBase64(data);
        return new String(bytes, Charset.forName("UTF-8"));
    }



    //MD5
    public static String md5(String data) {

        return DigestUtils.md5Hex(data);
    }

    //sha1
    public static String sha1(String data) {

        return DigestUtils.sha1Hex(data);
    }

    //sha256Hex
    public static String sha256Hex(String data) {

        return DigestUtils.sha256Hex(data);
    }


}
