package com.smiler.member.core.aop;

import com.smiler.member.core.annotation.AutoLock;
import com.smiler.member.util.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/5
 */
@Aspect
@Component
public class LockAspect extends AbstractAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Around("@annotation(com.smiler.member.core.annotation.AutoLock) && @annotation(autoLock)")
    private void autoLock(ProceedingJoinPoint joinPoint, AutoLock autoLock) {
        StandardEvaluationContext context = new StandardEvaluationContext();

        setParamNameAndValue(joinPoint,context);

        String name = parseParamBySpEL(autoLock.name(), context, String.class);
        String msg = parseParamBySpEL(autoLock.msg(), context, String.class);
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
}
