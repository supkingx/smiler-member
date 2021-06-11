package com.smiler.member.dao.user;


import com.smiler.member.model.po.UserPo;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/8
 */
public interface UserBaseMapper {

    List<UserPo> queryAllUsers();

    UserPo queryUserById(BigInteger id);

    void insertUser(List<UserPo> userPos);

    void updateUserById(List<UserPo> userPos);
}
