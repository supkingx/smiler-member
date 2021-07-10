package com.smiler.member.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
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
public class BlockHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlockHandler.class);

    public static List<UserVo> queryUsersComprehensiveBlockHandler(UserSo userSo, BlockException blockException) {
        LOGGER.warn("Blocked by Sentinel queryUsersComprehensiveBlockHandler,userSo:{},error msg:{}", JSON.toJSONString(userSo), blockException.getMessage());
        return Collections.emptyList();
    }
}
