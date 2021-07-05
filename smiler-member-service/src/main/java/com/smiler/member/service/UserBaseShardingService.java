package com.smiler.member.service;

import com.smiler.member.model.vo.UserVo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description: 基础类，使用sharding数据源
 * @Author: wangchao
 * @Date: 2021/7/3
 */
public interface UserBaseShardingService {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<UserVo> queryAllUsers();

    UserVo queryUserById(BigInteger id);

    void insertUser(List<UserVo> userVos);

    void updateUserById(List<UserVo> userPos);
}
