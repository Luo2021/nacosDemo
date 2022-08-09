package com.luoli.order.service;

import com.luoli.user.api.UserApi;
import com.luoli.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author liluo
 * @create 2022/7/8 10:57
 */
@Service
public class OrderService {

    @Autowired
    private UserApi userApi;

    public User queryUser(String username, String password) {
        User user = userApi.queryUser(username,password);
        return user;
    }
}
