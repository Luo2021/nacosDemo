package com.luoli.user.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 用户实体类
 * @Author liluo
 * @create 2022/7/5 16:51
 */
@TableName("tb_user")
@Data
public class User {
    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 用户名
     */
    @Length(max = 30, min = 2, message = "用户名长度只能在2-30之间")
    private String userName;

    /**
     * 密码
     */
    @Length(max = 30, min = 4, message = "密码长度只能在4-30之间")
    private String password;

    /**
     * 电话
     */
    @Pattern(regexp = "^1[35678]\\d{9}$",message = "手机号格式不正确")
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 是否删除
     */
    private boolean isDelete;

    /**
     * 密码的盐值
     */
    @JsonIgnore
    private String salt;

}
