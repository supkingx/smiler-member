package com.smiler.member.core.aop;

import com.smiler.member.core.annotation.AutoLock;
import com.smiler.member.util.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/5
 */
@Aspect
@Component
public class LockAspect {
    /**
     * 用于解析参数列表中的参数名称
     */
    private static final DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
    private static final ExpressionParser parser = new SpelExpressionParser();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Around("@annotation(com.smiler.member.core.annotation.AutoLock) && @annotation(autoLock)")
    private void autoLock(ProceedingJoinPoint joinPoint, AutoLock autoLock) {
        System.out.println("进入autoLock");
        // 获取参数的入参
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        // 获取参数名
        String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            // 设置spEL表达式上下文内容,将参数名 和 参数值 设置到context中
            context.setVariable(parameterNames[i], args[i]);
        }

        String name = parseSpELParam(autoLock.name(), context);
        String msg = parseSpELParam(autoLock.msg(), context);
        int expire = autoLock.expire();

        try {
            boolean lock = RedisUtils.redisLock(redisTemplate, name, expire);
            if (lock) {
                throw new RuntimeException(msg);
            }
            joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static String parseSpELParam(String spELParam, StandardEvaluationContext context) {
        if (!spELParam.contains("#")) {
            return spELParam;
        }
        Expression expression = parser.parseExpression(spELParam);
        return expression.getValue(context, String.class);
    }
}
