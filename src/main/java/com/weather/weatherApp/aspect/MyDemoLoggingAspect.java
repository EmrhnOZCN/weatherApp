package com.weather.weatherApp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class MyDemoLoggingAspect {

    //execution(public long get*())
    //execution(public long com.weather.weatherApp.service.adminService.getUserCount())
    //execution((* getUserCount())


    Logger log = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.weather.weatherApp.service.*.*(..))")
    public void forServicePackage(){}

    @Pointcut("execution(* com.weather.weatherApp.controller.*.*(..))")
    public void forControllerPackage(){}

    @Pointcut("execution(* com.weather.weatherApp.security.*.*(..))")
    public void forSecurityPackage(){}

    @Pointcut("forServicePackage() || forControllerPackage() ||forSecurityPackage()")
    public void forAppFlow(){}




    @Before("forAppFlow()")
    public void logBefor(JoinPoint joinPoint){

        String method = joinPoint.getSignature().toShortString();

        log.info("===>> in @Before calling method : " + " " + method);
    }


}
