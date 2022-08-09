package com.luoli.order.web;

import com.luoli.order.service.OrderService;
import com.luoli.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author liluo
 * @create 2022/7/8 10:58
 */
@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<User> queryUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestHeader(value="X-Request-red",required = false) String requestHead) {
        System.out.println("---------------------"+requestHead+"---------------------");
        return ResponseEntity.ok(orderService.queryUser(username, password));
    }
}
