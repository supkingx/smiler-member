package com.smiler.member.service.impl;

import com.google.common.base.Preconditions;
import com.smiler.member.constant.CommonConstant;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.dao2.user.UserMapper;
import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.UserBaseShardingService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/3
 */
@Service
@Transactional(rollbackFor = Exception.class, timeout = 10, transactionManager = CommonConstant.SHARDING_DATA_SOURCE_TRANSACTION_MANAGER)
public class UserBaseShardingServiceImpl implements UserBaseShardingService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrikaFacade orikaFacade;

    @Override
    public List<UserVo> queryAllUsers() {
        return orikaFacade.mapAsList(userMapper.queryAllUsers(), UserVo.class);
    }

    @Override
    public UserVo queryUserById(BigInteger id) {
        Preconditions.checkArgument(Objects.nonNull(id), "用户Id不能为空!");
        return orikaFacade.map(userMapper.queryUserById(id), UserVo.class);
    }

    @Override
    public void insertUser(List<UserVo> userVos) {
        if (CollectionUtils.isEmpty(userVos)) {
            return;
        }
        List<UserPo> userPos = orikaFacade.mapAsList(userVos, UserPo.class);
        userMapper.insertUser(userPos);
    }

    @Override
    public void updateUserById(List<UserVo> userVos) {
        if (CollectionUtils.isEmpty(userVos)) {
            return;
        }
        userMapper.updateUserById(orikaFacade.mapAsList(userVos, UserPo.class));
    }
}
