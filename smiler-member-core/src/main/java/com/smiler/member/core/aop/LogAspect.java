package com.smiler.member.core.aop;

import com.alibaba.fastjson.JSON;
import com.smiler.member.core.annotation.AutoLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 自动打印日志出入参
 * @Author: wangchao
 * @Date: 2021/8/3
 */
@Aspect
@Component
public class LogAspect extends AbstractAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Around("@annotation(com.smiler.member.core.annotation.AutoLog) && @annotation(autoLog)")
    private Object autoLog(ProceedingJoinPoint joinPoint, AutoLog autoLog) throws Throwable {

        StandardEvaluationContext standardEvaluationContext = new StandardEvaluationContext();
        Map<String, Object> requestParamMap = new HashMap<>();
        // 设置参数
        setParamNameAndValue(joinPoint, standardEvaluationContext, requestParamMap);

        Boolean isPrintLog = parseParamBySpEL(autoLog.condition(), standardEvaluationContext, Boolean.class);
        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        } finally {
            printLog(joinPoint, isPrintLog, requestParamMap, result);
        }
    }

    private void printLog(ProceedingJoinPoint joinPoint, Boolean isPrintLog, Map<String, Object> requestParamMap, Object result) {
        if (!isPrintLog) {
            return;
        }
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        StringBuilder logMsg = new StringBuilder();
        logMsg.append(className).append(".").append(methodName).append(".request:").append(JSON.toJSONString(requestParamMap));
        if (result != null) {
            logMsg.append(";response:").append(JSON.toJSONString(result));
        }
        LOGGER.info(logMsg.toString());
    }
}
