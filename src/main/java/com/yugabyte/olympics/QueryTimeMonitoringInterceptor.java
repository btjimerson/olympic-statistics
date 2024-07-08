package com.yugabyte.olympics;

import java.util.Date;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;

public class QueryTimeMonitoringInterceptor extends AbstractMonitoringInterceptor {

    @Override
    protected Object invokeUnderTrace(MethodInvocation invocation, Log logger) throws Throwable {

        Long start = System.currentTimeMillis();
        Long duration;
        System.out.println("I'm in the monitor");
        logger.warn("Started method invocation at " + new Date());
        try {
            invocation.proceed();
        } finally {
            Long end = System.currentTimeMillis();
            duration = end - start;
            logger.warn("Method lasteed " + duration + " ms");
        }

        return duration;
    }

}
