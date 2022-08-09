package com.luoli.common.vo;

import com.luoli.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * @Author liluo
 * @create 2022/7/6 10:37
 * 自定义异常结果类
 */
@Data
public class ExceptionResult {

    private int status;

    private String message;

    private long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.value();
        this.message = em.message();
        this.timestamp = System.currentTimeMillis();
    }
}
