package com.luoli.order;

import com.luoli.user.config.FeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author liluo
 * @create 2022/6/22 15:58
 */
@SpringBootApplication
@MapperScan("com.luoli.order.mapper")
@EnableFeignClients(basePackages = "com.luoli.user.api", defaultConfiguration = FeignConfiguration.class)
@EnableDiscoveryClient
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }
}
