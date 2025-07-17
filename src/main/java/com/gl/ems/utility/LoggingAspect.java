package com.gl.ems.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    public static final Log LOGGER= LogFactory.getLog(LoggingAspect.class);

    @AfterThrowing(pointcut ="execution(* com.gl.ems.service.*Impl.*(..))",throwing = "exception")
    public void logException(Exception exception){
        LOGGER.error(exception);
    }


}
