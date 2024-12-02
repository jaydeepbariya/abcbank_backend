package com.abcbank.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log method entry
    @Before("execution(* com.bank.service.*.*(..))")
    public void logMethodEntry() {
        logger.info("Entering method in service layer");
    }

    // Log exception when it is thrown
    @AfterThrowing(pointcut = "execution(* com.bank.service.*.*(..))", throwing = "exception")
    public void logException(Exception exception) {
        logger.error("Exception thrown: " + exception.getMessage(), exception);
    }
}

