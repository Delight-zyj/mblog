package com.mblog.exception;

import com.mblog.entry.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHander {

    /**
     * 处理用户名或密码错误异常
     * @param e 异常
     * @return 错误结果
     */
    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        log.error("程序出错了{}快快快", e);
        // 检查是否为用户名或密码错误
        if (e.getMessage() != null && e.getMessage().contains("用户名或密码错误")) {
            return Result.error("用户名或密码错误！");
        }
        return Result.error(e.getMessage());
    }

    /**
     * 处理其他所有异常
     * @param e 异常
     * @return 错误结果
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("程序出错了{}", e);
        return Result.error("出错了，请联系管理员");
    }

    /**
     * 处理DuplicateKeyException异常
     * @param e DuplicateKeyException异常
     * @return 错误结果
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错了{}", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] err = errMsg.split(" ");
        return Result.error(err[2] + "  重复了");
    }
}