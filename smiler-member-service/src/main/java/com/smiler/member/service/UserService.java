package com.smiler.member.service;

import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/26
 */
public interface UserService {

    void insertUser(List<UserVo> userVos);

    void addUserBatch(List<UserVo> userVos);

    /**
     * 综合搜索
     *
     * @param userSo
     * @return
     */
    List<UserVo> queryUsersComprehensive(UserSo userSo);

}
