package com.smiler.member.config;

import com.github.pagehelper.PageInterceptor;
import com.smiler.member.core.interceptor.SoInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @description: 集成pagehelper
 * @Author: wangchao
 * @Date: 2021/7/15
 */
public class AbstractMyBatisConfig {

    protected SqlSessionFactoryBean getSqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.smiler.member.model.po");
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        // 指定使用数据库
        properties.setProperty("helperDialect", "mysql");
        // pageSize为0时，不使用分页
        properties.setProperty("pageSizeZero", "true");
        // 页码<=0 查询第一页，页码>=总页数查询最后一页
        properties.setProperty("reasonable", "false");
        // 支持通过 Mapper 接口参数来传递分页参数
        properties.setProperty("supportMethodsArguments", "false");
        pageInterceptor.setProperties(properties);
        // 插件PageHelper
        factoryBean.setPlugins(pageInterceptor, new SoInterceptor());
        return factoryBean;
    }
}
