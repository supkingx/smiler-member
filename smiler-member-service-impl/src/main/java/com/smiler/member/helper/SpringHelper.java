package com.smiler.member.helper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @description: BeanFactoryPostProcessor:在BeanFactory准备工作完成后做一些定制化的处理
 * @Author: wangchao
 * @Date: 2021/7/19
 */
@Component
public class SpringHelper implements BeanFactoryPostProcessor {

    private static ConfigurableListableBeanFactory beanFactory;

    public static ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringHelper.beanFactory = beanFactory;
    }

    public static Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    public static  <T> T getBean(Class<T> clazz) {
        return beanFactory.getBean(clazz);
    }
}
