package com.smiler.member.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.smiler.member.constant.CommonConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/12
 */
@Configuration
@ConfigurationProperties("spring.datasource")
public class DataSourceConfig extends DruidDataSource {

    @Bean(CommonConstant.PRIMARY_DATA_SOURCE_NAME)
    public DataSource dataSource() {
        return this;
    }
}
