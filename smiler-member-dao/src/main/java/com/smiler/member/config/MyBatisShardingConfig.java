package com.smiler.member.config;

import com.smiler.member.constant.CommonConstant;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @description: 使用sharding数据源
 * @Author: wangchao
 * @Date: 2021/6/30
 */
@Configuration
public class MyBatisShardingConfig {

    @Bean(CommonConstant.SHARDING_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory2(@Autowired @Qualifier(CommonConstant.SHARDING_DATA_SOURCE_NAME) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper2/**/*.xml"));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer2() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(CommonConstant.SHARDING_SQL_SESSION_FACTORY);
        mapperScannerConfigurer.setBasePackage(CommonConstant.SHARDING_DAO_TWO_PATH);
        return mapperScannerConfigurer;
    }

    @Bean(CommonConstant.SHARDING_DATA_SOURCE_TRANSACTION_MANAGER)
    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier(CommonConstant.SHARDING_DATA_SOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
