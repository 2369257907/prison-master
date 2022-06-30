package com.api.handler;

import com.api.support.ResponseResult;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 捕获controller异常
 * controller抛出异常执行下边的函数
 * 返回Response写入ApiResult
 */
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e) {
        if (e.getClass().equals(ConstraintViolationException.class) || e.getClass().equals(DataIntegrityViolationException.class)) {
            return new ResponseResult<>(403, "用户名已存在");
        }
        System.out.println(e.getMessage());
        return  new ResponseResult<>(400, e.getMessage());
    }
}