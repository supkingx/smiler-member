package com.smiler.member.constant;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/11
 */
public class CommonConstant {

    /**
     * smiler_user 普通数据源
     */
    public static final String PRIMARY_DATA_SOURCE_NAME = "primaryDataSource";
    public static final String PRIMARY_SQL_SESSION_FACTORY = "primarySqlSessionFactory";
    public static final String PRIMARY_DATA_SOURCE_TRANSACTION_MANAGER = "primaryDataSourceTransactionManager";
    public static final String PRIMARY_DAO_PATH = "com.smiler.member.dao";

    /**
     * smiler_user sharding数据源
     */
    public static final String SHARDING_DATA_SOURCE_NAME = "shardingDataSource";
    public static final String SHARDING_SQL_SESSION_FACTORY = "shardingSqlSessionFactory";
    public static final String SHARDING_DATA_SOURCE_TRANSACTION_MANAGER = "shardingDataSourceTransactionManager";
    public static final String SHARDING_DAO_TWO_PATH = "com.smiler.member.dao2";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

}
