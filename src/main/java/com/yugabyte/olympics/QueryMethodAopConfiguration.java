package com.yugabyte.olympics;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@Aspect
@EnableAspectJAutoProxy
public class QueryMethodAopConfiguration {

    // @Pointcut("execution(public
    // java.util.List<com.yugabyte.olympics.entity.AthleteBefore>
    // com.yugabyte.olympics.repository.AthleteBeforeRepository.findTopMedalers(..))")
    @Pointcut("execution(public java.util.List<com.yugabyte.olympics.entity.MedalCount> com.yugabyte.olympics.controller.AthleteBeforeController.getTopMedalers())")
    public void athletesBeforeTopMedalersMonitor() {
    }

    @Bean
    public QueryTimeMonitoringInterceptor queryTimeMonitoringInterceptor() {
        return new QueryTimeMonitoringInterceptor();
    }

    @Bean
    public Advisor queryTimeMonitoringAdvisor() {

        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.yugabyte.olympics.QueryMethodAopConfiguration.athletesBeforeTopMedalersMonitor()");
        return new DefaultPointcutAdvisor(pointcut, queryTimeMonitoringInterceptor());

    }
}
