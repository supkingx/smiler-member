package com.smiler.member.service.impl;

import com.google.common.base.Preconditions;
import com.smiler.member.core.annotation.AutoLock;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.service.SmilerUserService;
import com.smiler.member.service.UserShardingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/6
 */
@Service
public class SmilerUserServiceImpl implements SmilerUserService {

    @Autowired
    private UserShardingService userShardingService;

    @Override
    @AutoLock(name = "#userVo.username", msg = "#userVo.username + '正在新建当中。。。'", expire = 5)
    public void addSmilerUser(UserVo userVo) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(userVo.getUsername()), "姓名不能为空!");
        Preconditions.checkArgument(StringUtils.isNotBlank(userVo.getBirthday()), "生日不能为空!");
        Preconditions.checkArgument(userVo.getGender() != null, "性别不能为空!");

        userShardingService.addUserBatch(Collections.singletonList(userVo));
    }
}
