package com.luoli.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luoli.user.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author liluo
 * @create 2022/7/5 17:36
 */
public interface UserMapper extends BaseMapper<User> {

    Integer selectCount(@Param("user") User user);

    User selectOne(@Param("user") User user);
}
