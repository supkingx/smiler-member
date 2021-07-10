package com.smiler.member.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.Preconditions;
import com.smiler.member.core.annotation.AutoLock;
import com.smiler.member.core.orika.OrikaFacade;
import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;
import com.smiler.member.search.api.user.facade.UserSearchFacade;
import com.smiler.member.search.api.user.model.UserResponse;
import com.smiler.member.search.model.so.UserSearch;
import com.smiler.member.sentinel.BlockHandler;
import com.smiler.member.sentinel.FallbackHandler;
import com.smiler.member.service.SmilerUserService;
import com.smiler.member.service.UserShardingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/6
 */
@Service
public class SmilerUserServiceImpl implements SmilerUserService {

    @Autowired
    private UserShardingService userShardingService;

    @Reference
    private UserSearchFacade userSearchFacade;

    @Autowired
    private OrikaFacade orikaFacade;

    @Override
    @AutoLock(name = "#userVo.username", msg = "#userVo.username + '正在新建当中。。。'", expire = 5)
    public void addSmilerUser(UserVo userVo) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(userVo.getUsername()), "姓名不能为空!");
        Preconditions.checkArgument(StringUtils.isNotBlank(userVo.getBirthday()), "生日不能为空!");
        Preconditions.checkArgument(userVo.getGender() != null, "性别不能为空!");
        userShardingService.addUserBatch(Collections.singletonList(userVo));
    }

    @Override
    @SentinelResource(value = "queryUsersComprehensive", blockHandlerClass = BlockHandler.class, blockHandler = "queryUsersComprehensiveBlockHandler",
            fallbackClass = FallbackHandler.class, fallback = "queryUsersComprehensiveFallbackHandler")
    public List<UserVo> queryUsersComprehensive(UserSo userSo) {
        List<UserResponse> userResponses = userSearchFacade.queryUsersComprehensive(orikaFacade.map(userSo, UserSearch.class));
        return orikaFacade.mapAsList(userResponses, UserVo.class);
    }
}
