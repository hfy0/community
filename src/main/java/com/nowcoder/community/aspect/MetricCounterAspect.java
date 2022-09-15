package com.nowcoder.community.aspect;

import com.nowcoder.community.MetricCounter.Aggregator;
import com.nowcoder.community.MetricCounter.MetricsCollector;
import com.nowcoder.community.MetricCounter.MetricsStorage.MemoryMetricsStorage;
import com.nowcoder.community.MetricCounter.MetricsStorage.MetricsStorage;
import com.nowcoder.community.MetricCounter.Reporter.ConsoleReporter;
import com.nowcoder.community.MetricCounter.RequestInfo;
import com.nowcoder.community.MetricCounter.Viewer.ConsoleViewer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class MetricCounterAspect {
    @Autowired
    MetricsCollector collector;

    @Around("execution(* com.nowcoder.community.controller.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long before = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long after = System.currentTimeMillis();
        // 方法签名
        String apiName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        collector.recordRequest(new RequestInfo(apiName, after - before, before));
        return result;
    }
}
