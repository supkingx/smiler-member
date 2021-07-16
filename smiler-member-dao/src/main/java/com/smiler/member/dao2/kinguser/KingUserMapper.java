package com.smiler.member.dao2.kinguser;

import com.smiler.member.model.po.KingUserPo;
import com.smiler.member.model.so.KingUserSo;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/14
 */
public interface KingUserMapper {

    /**
     * 查询所有king用户
     * @return
     */
    List<KingUserPo> queryAllKinUser(KingUserSo so);
}
