package com.masmovil.challenge.aspect;

import com.masmovil.challenge.domain.Order;
import com.masmovil.challenge.domain.Phone;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jcorredera on 17/02/18.
 */
@Aspect
@Component
public class ControllerLoggerAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerLoggerAspect.class);

    @Before("execution(public * com.masmovil.challenge.controllers.*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        Object[] args=pjp.getArgs();
        if(args.length > 0 ) {
            for (Object arg : args) {
                if (arg instanceof Phone) {
                    LOGGER.info("[REST-API]: data recived => {} ", ((Phone) arg).toString());
                }
                if (arg instanceof Order) {
                    LOGGER.info("[REST-API]: data recived => {} ", ((Order) arg).toString());

                }
            }
        }
        else {
            LOGGER.info("[REST-API]: method invocated => {}", pjp.getSignature());
        }
    }

    @AfterReturning(pointcut = "execution(public * com.masmovil.challenge.controllers.*Controller.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        if (result != null) {
            LOGGER.info("[REST-API]: class : {} => method : {} ", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
            LOGGER.info("[REST-API]: return => {}", result.toString());
        }
    }

}
