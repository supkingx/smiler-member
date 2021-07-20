package com.smiler.member.sentinel;

import com.alibaba.fastjson.JSON;
import com.smiler.member.model.so.UserSo;
import com.smiler.member.model.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/10
 */
public class FallbackHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockHandler.class);

    public static List<UserVo> queryUsersComprehensiveFallbackHandler(UserSo userSo,Throwable e) {
        LOGGER.error("Fallback by Sentinel queryUsersComprehensiveFallbackHandler,userSo:{}", JSON.toJSONString(userSo),e);
        return Collections.emptyList();
    }
}
