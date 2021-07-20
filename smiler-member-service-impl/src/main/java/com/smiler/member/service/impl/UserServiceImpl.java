package com.smiler.member.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.smiler.member.core.idWorker.IdGenerate;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.helper.UserEsMsgSenderHelper;
import com.smiler.member.model.enums.MessageTypeEnum;
import com.smiler.member.model.po.UserPo;
import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserIndexMessageVo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.search.api.user.facade.UserSearchFacade;
import com.smiler.member.search.api.user.model.UserResponse;
import com.smiler.member.search.model.so.UserSearch;
import com.smiler.member.service.UserBaseService;
import com.smiler.member.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserEsMsgSenderHelper userEsMsgSenderHelper;

    @Reference
    private UserSearchFacade userSearchFacade;

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
    public void addUserBatch(List<UserVo> userVos) {
        List<BigInteger> idList = IdGenerate.getIdList(userVos.size());
        AtomicInteger i = new AtomicInteger(0);
        for (UserVo userVo : userVos) {
            userVo.setId(idList.get(i.getAndIncrement()));
        }
        userBaseService.insertUserByIdGenerate(userVos);
        // 发送消息到member-search
        userEsMsgSenderHelper.sendMessage(idList, MessageTypeEnum.USER_ADD.getType());
    }

    @Override
    public List<UserVo> queryUsersComprehensive(UserSo userSo) {
        List<UserResponse> userResponses = userSearchFacade.queryUsersComprehensive(orikaFacade.map(userSo, UserSearch.class));
        List<UserVo> userVos = orikaFacade.mapAsList(userResponses, UserVo.class);
        return userVos;
    }
}
