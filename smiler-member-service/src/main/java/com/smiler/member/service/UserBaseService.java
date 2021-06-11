package com.smiler.member.service;


import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.vo.UserVo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/8
 */
public interface UserBaseService {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<UserPo> queryAllUsers();

    String testService();

    UserPo queryUserById(BigInteger id);

    void insertUser(List<UserVo> userVos);

    void updateUserById(List<UserVo> userPos);
}
