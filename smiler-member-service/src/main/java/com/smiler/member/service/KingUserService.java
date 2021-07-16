package com.smiler.member.service;

import com.github.pagehelper.PageInfo;
import com.smiler.member.model.so.KingUserSo;
import com.smiler.member.model.vo.KingUserVo;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/14
 */
public interface KingUserService {

    List<KingUserVo> queryAllKinUser(KingUserSo so);

    PageInfo<KingUserVo> queryAllKinUserPage(KingUserSo so);
}
