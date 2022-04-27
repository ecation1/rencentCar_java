package com.example.demo.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotPermissionException.class)
    public SaResult NotPermissin(NotPermissionException noPermission){
        return SaResult.error("你没有此权限");
    }
    @ExceptionHandler({NotLoginException.class})
    public SaResult NoLogin(NotLoginException e){
        return SaResult.get(401,"请先登录",null);
    }
}
