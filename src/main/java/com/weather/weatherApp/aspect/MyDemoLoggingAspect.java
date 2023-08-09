package com.weather.weatherApp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

// AOP yönü kesiti olarak işaretlenir.
@Aspect
@Component
public class MyDemoLoggingAspect {

    // Java Logger nesnesi oluşturulur.
    Logger log = Logger.getLogger(getClass().getName());

    // "com.weather.weatherApp" paketi içindeki tüm metodlar için bir kesit tanımlanır.
    @Pointcut("execution(* com.weather.weatherApp..*.*(..))")
    public void forAppPackage() {}

    // "com.weather.weatherApp.aspect" paketi içindeki tüm metodlar için bir kesit tanımlanır.
    @Pointcut("execution(* com.weather.weatherApp.aspect..*.*(..))")
    public void forAspectPackage() {}

    // "forAppPackage" kesiti "forAspectPackage" kesiti hariç olmak üzere uygulandığında bir kesit tanımlanır.
    @Pointcut("forAppPackage() && !forAspectPackage()")
    public void forAppFlow() {}

    // "forAppFlow" kesiti uygulandığında, ilgili metod öncesi çalışacak olan bir yöntem tanımlanır.
    @Before("forAppFlow()")
    public void logBefore(JoinPoint joinPoint) {
        // Çağrılan metodu loglar.
        String method = joinPoint.getSignature().toShortString();
        log.info("===>> in @Before calling method: " + method);
    }
}
