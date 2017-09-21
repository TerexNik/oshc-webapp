package ru.OSHC.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggerAspect {
    private static final Logger log = Logger.getLogger(LoggerAspect.class);

    @Pointcut("@annotation(ru.OSHC.annotation.Loggable)")
    public void loggableMethod(){}

    @Pointcut("execution(* ru.OSHC.advice..*(..))")
    public void exceptionMethod(){}

    @Pointcut("execution(* ru.OSHC.controller..*(..))")
    public void controllerMethod(){}

    @Around("loggableMethod() && exceptionMethod()")
    public Object logException(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();

        log.error("Error: " + methodName + " with args " + Arrays.toString(methodArgs));

        Object result = thisJoinPoint.proceed();

        log.error("Error: " + methodName + " returns " + result);

        return result;
    }

    @Around("loggableMethod() && controllerMethod()")
    public Object logServiceCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();

        log.debug("Call method " + methodName + " with args " + Arrays.toString(methodArgs));

        Object result = thisJoinPoint.proceed();

        log.debug("Method " + methodName + " returns " + result);

        return result;
    }
}
