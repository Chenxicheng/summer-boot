/**
 * Copyright &copy; 2015 <a href="https://github.com/snieri/xiyou">JeeSite</a> All rights reserved.
 */
package com.summer.commen.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author WangYanLiang
 * @version 2015-01-15
 */
@Service
@Lazy(false)
public class IdGen{

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
//	public static String randomBase62(int length) {
//		byte[] randomBytes = new byte[length];
//		random.nextBytes(randomBytes);
//		return Encodes.encodeBase62(randomBytes);
//	}


}
