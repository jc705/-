package com.caterpillar.zyzshop.controller.Code;


import com.caterpillar.zyzshop.exception.BusinessException;
import com.caterpillar.zyzshop.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public Result doSystemExcpetion(SystemException ex){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        return new Result(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessExcpetion(BusinessException ex){
        return new Result(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doExcpetion(Exception ex){
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        return new Result(Codes.SYSTEM_UNKNOW_ERR, "系统繁忙请稍后再试！");
    }
}
