package com.smiler.member.service.impl;

import com.smiler.member.constant.CommonConstant;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.dao.user.UserBaseMapper;
import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.UserBaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBaseServiceImpl.class);

    @Autowired
    private UserBaseMapper userBaseMapper;

    @Autowired
    protected OrikaFacade orikaFacade;

    @Override
    public List<UserPo> queryAllUsers() {
        return userBaseMapper.queryAllUsers();
    }

    @Override
    public String testLog() {
        LOGGER.error("测试error日志");
        LOGGER.info("测试info日志");
        LOGGER.warn("测试warn日志");
        LOGGER.debug("测试debug日志");
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
    public void insertUserByIdGenerate(List<UserVo> userVos) {
        if (CollectionUtils.isEmpty(userVos)) {
            return;
        }
        userBaseMapper.insertUserByIdGenerate(orikaFacade.mapAsList(userVos, UserPo.class));
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
