package com.mjt.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice
public class GloabalExceptionHandler {
    //用于处理删除父类信息时，因外键所导致的异常处理

    @ExceptionHandler(value= Exception.class)
    public String defaultErrorHandler(HttpServletRequest request, Exception e) throws ClassNotFoundException {
        e.printStackTrace();
        //获取类对象
        Class constraintViolationException = Class.forName("org.hibernate.exception.ConstraintViolationException");
        //判断出错的类对象是否和上面的一致
        if(null != e.getCause() && constraintViolationException==e.getCause().getClass()){
            return "违反了约束，多半是外键约束";
        }

        return e.getMessage();
    }

}
