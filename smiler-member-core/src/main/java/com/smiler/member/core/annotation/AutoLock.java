package com.smiler.member.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/5
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoLock {

    /**
     * 锁名
     *
     * @return
     */
    String name();

    /**
     * 锁自动过期时间
     *
     * @return
     */
    int expire() default 10;

    /**
     * 提示语
     *
     * @return
     */
    String msg() default "操作进行中,请勿重复操作";
}
