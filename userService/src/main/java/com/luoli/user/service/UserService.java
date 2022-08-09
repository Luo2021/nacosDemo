package com.luoli.user.service;

import com.luoli.common.enums.ExceptionEnum;
import com.luoli.common.exception.LuoLiException;
import com.luoli.common.utils.CodecUtils;
import com.luoli.common.utils.NumberUtils;
import com.luoli.user.mapper.UserMapper;
import com.luoli.user.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author liluo
 * @create 2022/7/5 17:35
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify:code:";


    public Boolean checkData(String data, Integer type) {
        User user = new User();
        //判断校验数据的类型
        switch (type) {
            case 1:
                user.setUserName(data);
                break;
            case 2:
                user.setPhone(data);
                break;
            default:
                throw new LuoLiException(ExceptionEnum.INVALID_PARAM);
        }
        return (userMapper.selectCount(user) == 0);
    }

    public void sendVerifyCode(String phone) {

        //随机生成6位数字验证码
        String code = NumberUtils.generateCode(6);

        String key = KEY_PREFIX + phone;

        //把验证码放入Redis中，并设置有效期为5min
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);

        //向mq中发送消息
        Map<String,String> map = new HashMap();
        map.put("phone", phone);
        map.put("code", code);
        amqpTemplate.convertAndSend("ly.sms.exchange", "sms.verify.code", map);
    }

    public void register(User user, String code) {
        user.setId(null);
        String key = KEY_PREFIX + user.getPhone();

        String value = redisTemplate.opsForValue().get(key);

        if (!StringUtils.equals(code, value)) {
            //验证码不匹配
            throw new LuoLiException(ExceptionEnum.VERIFY_CODE_NOT_MATCHING);
        }

        //生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        //生成密码
        String md5Pwd = CodecUtils.md5Hex(user.getPassword(), user.getSalt());

        user.setPassword(md5Pwd);

        //保存到数据库
        int count = userMapper.insert(user);

        if (count != 1) {
            throw new LuoLiException(ExceptionEnum.INVALID_PARAM);
        }

        //把验证码从Redis中删除
        redisTemplate.delete(key);


    }

    public User queryUser(String username, String password) {
        User record = new User();
        record.setUserName(username);
        //首先根据用户名查询用户
        User user = userMapper.selectOne(record);

        if (user == null) {
            throw new LuoLiException(ExceptionEnum.USER_NOT_EXIST);
        }

        //检验密码是否正确
        if (!StringUtils.equals(CodecUtils.md5Hex(password, user.getSalt()), user.getPassword())) {
            //密码不正确
            throw new LuoLiException(ExceptionEnum.PASSWORD_NOT_MATCHING);
        }

        return user;
    }
}

