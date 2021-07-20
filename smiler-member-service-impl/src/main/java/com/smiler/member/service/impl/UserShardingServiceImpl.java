package com.smiler.member.service.impl;

import com.smiler.member.core.idWorker.IdGenerate;
import com.smiler.member.model.enums.MessageTypeEnum;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.UserBaseShardingService;
import com.smiler.member.service.UserShardingService;
import com.smiler.member.helper.UserEsMsgSenderHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/3
 */
@Service
public class UserShardingServiceImpl implements UserShardingService {

    @Autowired
    private UserBaseShardingService userBaseShardingService;

    @Autowired
    private UserEsMsgSenderHelper userEsMsgSenderHelper;

    @Override
    public void addUserBatch(List<UserVo> userVos) {
        List<BigInteger> idList = IdGenerate.getIdList(userVos.size());
        AtomicInteger i = new AtomicInteger(0);
        for (UserVo userVo : userVos) {
            userVo.setId(idList.get(i.getAndIncrement()));
        }
        userBaseShardingService.insertUser(userVos);
        userEsMsgSenderHelper.sendSuperMessage(idList, MessageTypeEnum.USER_ADD.getType());
    }

    @Override
    public UserVo queryUserById(BigInteger id) {
        return userBaseShardingService.queryUserById(id);
    }

    @Override
    public void updateUserById(List<UserVo> userPos) {
        userBaseShardingService.updateUserById(userPos);
    }

    @Override
    public List<UserVo> queryAllUsers() {
        List<UserVo> userVos = userBaseShardingService.queryAllUsers();
        return userVos;
    }
}
