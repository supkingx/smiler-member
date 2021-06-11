package com.smiler.member.service.impl;

import com.smiler.member.constant.CommonConstant;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.dao.user.UserBaseMapper;
import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.UserBaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/12
 */

@Service
@Transactional(rollbackFor = Exception.class, timeout = 2, transactionManager = CommonConstant.PRIMARY_DATA_SOURCE_TRANSACTION_MANAGER)
public class UserBaseServiceImpl implements UserBaseService {

    @Autowired
    private UserBaseMapper userBaseMapper;

    @Autowired
    protected OrikaFacade orikaFacade;

    @Override
    public List<UserPo> queryAllUsers() {
        return userBaseMapper.queryAllUsers();
    }

    @Override
    public String testService() {
        return "king";
    }

    @Override
    @Cacheable(value = "queryUserById", key = "#id")
    public UserPo queryUserById(BigInteger id) {
        return userBaseMapper.queryUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 2, transactionManager = CommonConstant.PRIMARY_DATA_SOURCE_TRANSACTION_MANAGER)
    public void insertUser(List<UserVo> userVos) {
        if (CollectionUtils.isEmpty(userVos)) {
            return;
        }
        userBaseMapper.insertUser(orikaFacade.mapAsList(userVos, UserPo.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 2, transactionManager = CommonConstant.PRIMARY_DATA_SOURCE_TRANSACTION_MANAGER)
    public void updateUserById(List<UserVo> userVos) {
        if (CollectionUtils.isEmpty(userVos)) {
            return;
        }
        userBaseMapper.updateUserById(orikaFacade.mapAsList(userVos, UserPo.class));
    }
}
