package com.masmovil.challenge.aspect;

import com.masmovil.challenge.domain.Order;
import com.masmovil.challenge.domain.Phone;
import org.aspectj.lang.JoinPoint;
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
public class RepositoryLoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryLoggerAspect.class);

    @Before("execution(public * com.masmovil.challenge.repository.*Repository.*(..))")
    public void logBeforeEventServiceCall(JoinPoint pjp) throws Throwable {
        Object[] args=pjp.getArgs();
        if(args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof Phone) {
                    LOGGER.info("[REPOSITORY]: executed method: {}() => {} ", pjp.getSignature().getName(), ((Phone) arg).toString());
                }
                else if(arg instanceof Order) {
                    LOGGER.info("[REPOSITORY]: executed method: {}() => {} ", pjp.getSignature().getName(), ((Order) arg).toString());
                }
//                else if(arg instanceof Integer) {
//                    LOGGER.info("[REPOSITORY]: executed method: {}() => {} ", pjp.getSignature().getName(), ((Integer) arg).toString());
//                }
            }
        }
        else {
            LOGGER.info("[REPOSITORY]: method invocated => {}", pjp.getSignature());
        }
    }

}
