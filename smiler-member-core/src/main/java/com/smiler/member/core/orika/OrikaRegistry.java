package com.smiler.member.core.orika;

import ma.glasnost.orika.MapperFactory;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/30
 */
public interface OrikaRegistry {

    /**
     * Orika自定义注册
     *
     * @param factory
     */
    void register(MapperFactory factory);
}
