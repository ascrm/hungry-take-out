package com.hungry.aspact;

import com.hungry.Enum.OperationType;
import com.hungry.annotation.AutoFill;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class AutoFillAop {

    @Pointcut("execution(* com.hungry.service.impl.*.*(..)) && @annotation(com.hungry.annotation.AutoFill)")
    public void pointcut(){}

    @Before("pointcut()")
    public void AutoFill(JoinPoint joinPoint){
        log.info("公共字段字段填充");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType value = autoFill.value();
        Object entyty = joinPoint.getArgs()[0];
        if(value == OperationType.ORDER_INSERT){
            try {
                Method setStartTime = entyty.getClass().getDeclaredMethod("setStartTime", LocalDateTime.class);
                Method setEndTime = entyty.getClass().getDeclaredMethod("setEndTime", LocalDateTime.class);

                setStartTime.invoke(entyty,LocalDateTime.now());
                setEndTime.invoke(entyty,LocalDateTime.now());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if(value == OperationType.ORDER_UPDATE){
            try {
                Method setEndTime = entyty.getClass().getDeclaredMethod("setEndTime", LocalDateTime.class);
                setEndTime.invoke(entyty,LocalDateTime.now());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(value == OperationType.DISH_INSERT){
            try {
                Method setCreateTime = entyty.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
                Method setUpdateTime = entyty.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);

                setCreateTime.invoke(entyty,LocalDateTime.now());
                setUpdateTime.invoke(entyty,LocalDateTime.now());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(value == OperationType.DISH_UPDATE){
            try {
                Method setUpdateTime = entyty.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                setUpdateTime.invoke(entyty,LocalDateTime.now());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
