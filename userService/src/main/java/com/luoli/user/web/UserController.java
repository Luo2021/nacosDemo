package com.luoli.user.web;

import com.luoli.user.pojo.User;
import com.luoli.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author liluo
 * @create 2022/7/5 17:34
 */
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${luo.li}")
    private String test;

    @GetMapping("/test")
    public String getTest(){
        return test;
    }

    /**
     * 校验数据
     * @param data
     * @param type
     * @return
     */
    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkData(@PathVariable("data") String data, @PathVariable("type") Integer type) {
        return ResponseEntity.ok(userService.checkData(data, type));
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @PostMapping("send")
    public ResponseEntity<Void> sendVerifyCode(@RequestParam("phone")String phone) {
        userService.sendVerifyCode(phone);
        return ResponseEntity.noContent().build();
    }

    /**
     * 用户注册
     * @param user
     * @param code
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody @Valid User user, @RequestParam("code")String code) {
        userService.register(user, code);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<User> queryUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestHeader(value="X-Request-red",required = false) String requestHead) {
        System.out.println("---------------------"+requestHead+"---------------------");
        return ResponseEntity.ok(userService.queryUser(username, password));
    }


}