package com.luoli.common.advice;

import com.luoli.common.exception.LuoLiException;
import com.luoli.common.vo.ExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author liluo
 * @create 2022/7/6 10:26
 * 自定义异常处理
 */
@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(LuoLiException.class)
    public ResponseEntity<ExceptionResult> handleException(LuoLiException e) {
        return ResponseEntity.status(e.getExceptionEnum().value())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }
}