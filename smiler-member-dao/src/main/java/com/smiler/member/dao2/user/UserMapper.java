package com.smiler.member.dao2.user;

import com.smiler.member.model.po.UserPo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/1
 */
public interface UserMapper {

    List<UserPo> queryAllUsers();

    UserPo queryUserById(BigInteger id);

    void insertUser(List<UserPo> userPos);

    void updateUserById(List<UserPo> userPos);

    void addKingUser(List<UserPo> userPos);
}
