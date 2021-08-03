package com.smiler.member.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 自动打印日志
 * @Author: wangchao
 * @Date: 2021/8/3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLog {

    /**
     * 根据条件输出日志 支持SPEL
     *
     * @return
     */
    String condition() default "true";
}
