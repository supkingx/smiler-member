package com.smiler.member.service;

import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/6
 */
public interface SmilerUserService {

    void addSmilerUser(UserVo userVo);

    List<UserVo> queryUsersComprehensive(UserSo userSo);
}
