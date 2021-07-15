package com.smiler.member.service.impl;

import com.github.pagehelper.PageInfo;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.dao2.kinguser.KingUserMapper;
import com.smiler.member.model.po.KingUserPo;
import com.smiler.member.model.so.KingUserSo;
import com.smiler.member.model.vo.KingUserVo;
import com.smiler.member.service.KingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/15
 */
@Service
public class KingUserServiceImpl implements KingUserService {

    @Autowired
    private KingUserMapper kingUserMapper;

    @Autowired
    private OrikaFacade orikaFacade;

    @Override
    public List<KingUserVo> queryAllKinUser(KingUserSo kingUserSo) {
        List<KingUserPo> userPos = kingUserMapper.queryAllKinUser(kingUserSo);
        kingUserSo.setEnableCount(false);
        return orikaFacade.mapAsList(userPos, KingUserVo.class);
    }

    @Override
    public PageInfo<KingUserVo> queryAllKinUserPage(KingUserSo so) {
        List<KingUserPo> userPos = kingUserMapper.queryAllKinUser(so);
        List<KingUserVo> kingUserVoList = orikaFacade.mapAsList(userPos, KingUserVo.class);
        PageInfo<KingUserVo> pageInfo = new PageInfo<>(kingUserVoList);
        return pageInfo;
    }
}
