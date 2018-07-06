package com.privalia.mydemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//@Aspect
//public class LoggingAspect {
//    @Around("execution(* com.privalia.mydemo..*.*(..))")
//    public Object logEntryAndExit(ProceedingJoinPoint joinPoint) throws Throwable {
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        log.debug("Method entry: " + className + "." + methodName);
//        Object retVal = joinPoint.proceed();
//        log.debug("Method exit: " + className + "." + methodName);
//        return true;
//    }
//}
