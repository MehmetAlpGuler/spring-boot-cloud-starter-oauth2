package com.oauth.accesstoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisTokenStoreResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisTokenStoreResourceServerApplication.class, args);
	}
}
