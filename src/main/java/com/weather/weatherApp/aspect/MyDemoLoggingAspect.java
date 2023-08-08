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

    @Pointcut("execution(* com.weather.weatherApp..*.*(..))")
    public void forAppPackage() {}

    @Pointcut("execution(* com.weather.weatherApp.aspect..*.*(..))")
    public void forAspectPackage() {}

    @Pointcut("forAppPackage() && !forAspectPackage()")
    public void forAppFlow() {}

    @Before("forAppFlow()")
    public void logBefore(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        log.info("===>> in @Before calling method: " + method);
    }


}
