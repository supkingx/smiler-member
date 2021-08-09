package com.smiler.member.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @description: 切面抽象方法
 * @Author: wangchao
 * @Date: 2021/8/3
 */
public class AbstractAspect {

    private static final String POUND = "#";

    /**
     * SpEl语法解析
     */
    private static ExpressionParser spelExpressionParser = new SpelExpressionParser();

    private static DefaultParameterNameDiscoverer DISCOVER_PARAM_NAME = new DefaultParameterNameDiscoverer();

    protected <T> T parseParamBySpEL(String spElParam, StandardEvaluationContext standardEvaluationContext, Class<T> clazz) {
        if (!spElParam.contains(POUND)) {
            return (T) spElParam;
        }
        Expression expression = spelExpressionParser.parseExpression(spElParam);
        return expression.getValue(standardEvaluationContext, clazz);
    }

    /**
     * 设置参数列表的参数名称和值到指定的容器中
     *
     * @param joinPoint
     * @param container 装载 参数值和参数名的容器
     */
    protected void setParamNameAndValue(ProceedingJoinPoint joinPoint, Object... container) {
        // 获取入参的值
        Object[] args = joinPoint.getArgs();
        // 获取方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取方法名
        String[] parameterNames = DISCOVER_PARAM_NAME.getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
            for (Object object : container) {
                if (object instanceof Map) {
                    ((Map<String, Object>) object).put(parameterNames[i], args[i]);
                }
                if (object instanceof EvaluationContext) {
                    ((EvaluationContext) object).setVariable(parameterNames[i], args[i]);
                }
            }
        }
    }
}
