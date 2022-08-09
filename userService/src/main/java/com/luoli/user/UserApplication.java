package com.luoli.user;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author liluo
 * @create 2022/6/22 16:02
 */
@SpringBootApplication
@MapperScan("com.luoli.user.mapper")
@EnableDiscoveryClient
public class UserApplication {
    private static final Logger log = LoggerFactory.getLogger(UserApplication.class);
    public static void main(String[] args) {
        log.info("ServerApplication start.");
        SpringApplication.run(UserApplication.class);
    }
}
