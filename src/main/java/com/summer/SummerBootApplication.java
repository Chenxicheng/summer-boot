package com.summer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

//启用缓存
@EnableCaching
//启用异步
@EnableAsync
@SpringBootApplication
public class SummerBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SummerBootApplication.class, args);
	}
}
