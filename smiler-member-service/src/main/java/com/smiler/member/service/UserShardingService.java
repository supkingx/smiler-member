package com.smiler.member.service;

import com.smiler.member.model.vo.UserVo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/3
 */
public interface UserShardingService {

    void addUserBatch(List<UserVo> userVos);

    UserVo queryUserById(BigInteger id);

    void updateUserById(List<UserVo> userPos);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<UserVo> queryAllUsers();
}
