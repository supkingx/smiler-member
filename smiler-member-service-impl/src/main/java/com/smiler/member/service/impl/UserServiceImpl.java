package com.smiler.member.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.model.po.UserIndexMessageVo;
import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.search.api.user.facade.UserFacade;
import com.smiler.member.search.api.user.model.UserResponse;
import com.smiler.member.service.UserBaseService;
import com.smiler.member.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/26
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Reference
    private UserFacade userFacade;

    @Autowired
    private OrikaFacade orikaFacade;

    @Override
    public void insertUser(List<UserVo> userPos) {
        userBaseService.insertUser(userPos);
        List<UserPo> users = userBaseService.queryAllUsers();
        List<BigInteger> ids = users.stream().map(UserPo::getId).collect(Collectors.toList());
        // 发送消息到member-search
        UserIndexMessageVo userIndexMessageVo = new UserIndexMessageVo();
        userIndexMessageVo.setIds(ids);
        rabbitTemplate.convertAndSend("smiler.member.direct", "smiler.user.index", JSON.toJSONString(userIndexMessageVo));
    }

    @Override
    public List<UserVo> queryUsersComprehensive(UserSo userSo) {
        List<UserResponse> userResponses = userFacade.queryUsersComprehensive(userSo);
        List<UserVo> userVos = orikaFacade.mapAsList(userResponses, UserVo.class);
        return userVos;
    }
}
