package com.smiler.member.dao.config;

import com.smiler.member.constant.CommonConstant;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/12
 */
@Configuration
@ConditionalOnBean(name = CommonConstant.PRIMARY_DATA_SOURCE_NAME)
public class MybatisConfig {

    @Bean(CommonConstant.PRIMARY_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier(CommonConstant.PRIMARY_DATA_SOURCE_NAME) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/**/*.xml"));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(CommonConstant.PRIMARY_SQL_SESSION_FACTORY);
        mapperScannerConfigurer.setBasePackage(CommonConstant.PRIMARY_DAO_PATH);
        return mapperScannerConfigurer;
    }

    @Bean(CommonConstant.PRIMARY_DATA_SOURCE_TRANSACTION_MANAGER)
    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier(CommonConstant.PRIMARY_DATA_SOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
