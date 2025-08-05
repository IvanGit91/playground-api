package com.playground.api.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class LoggingAspect {

    // Define a pointcut: Target all methods in the service package
    @Pointcut("execution(* com.playground.api.service.*.*(..))")
    public void serviceMethods() {}

    // Advice that runs before the method execution
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Executing method: " + joinPoint.getSignature());
    }

    // Advice that runs after the method execution
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Completed method: " + joinPoint.getSignature());
    }

    // Advice that runs after the method returns
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Method returned: " + result);
    }

    // Advice that runs if the method throws an exception
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.info("Method threw exception: " + error.getMessage());
    }
}
