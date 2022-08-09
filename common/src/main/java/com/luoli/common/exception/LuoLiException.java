package com.luoli.common.exception;

import com.luoli.common.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @Author liluo
 * @create 2022/7/6 10:09
 */
@Getter
public class LuoLiException  extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public LuoLiException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }


}

