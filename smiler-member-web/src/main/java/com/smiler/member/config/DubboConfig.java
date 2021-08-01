package com.smiler.member.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.smiler.member.helper.SpringHelper;
import com.smiler.member.search.api.user.facade.UserSearchFacade;
import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/22
 */
@Lazy
@Configuration
public class DubboConfig {

    @Bean
    public ReferenceBean<UserSearchFacade> userSearchFacade() {
        return registerReference(UserSearchFacade.class);
    }

    protected <T> ReferenceBean<T> registerReference(Class<T> clazz) {
        ReferenceBean<T> referenceBean = new ReferenceBean<>();
        referenceBean.setInterface(clazz);
        referenceBean.setRegistry(SpringHelper.getBean(RegistryConfig.class));
        referenceBean.setApplication(SpringHelper.getBean(ApplicationConfig.class));
        referenceBean.setProtocol(DubboProtocol.NAME);
        referenceBean.setConsumer(SpringHelper.getBean(ConsumerConfig.class));
//        referenceBean.setFilter();
        referenceBean.setRetries(0);
        referenceBean.setGroup(referenceBean.getConsumer().getGroup());
        return referenceBean;
    }

    protected <T> ServiceBean<T> registerService(Class<T> clazz, T ref) {
        ServiceBean<T> serviceBean = new ServiceBean<>();
        serviceBean.setInterface(clazz);
        serviceBean.setApplication(SpringHelper.getBean(ApplicationConfig.class));
        serviceBean.setProvider(SpringHelper.getBean(ProviderConfig.class));
        serviceBean.setRegistry(SpringHelper.getBean(RegistryConfig.class));
        serviceBean.setProtocols(Lists.newArrayList(SpringHelper.getBeanFactory().getBeansOfType(ProtocolConfig.class).values()));
        serviceBean.setRef(ref);
        serviceBean.setGroup(serviceBean.getProvider().getGroup());
        serviceBean.setRetries(0);
        return serviceBean;
    }

}
