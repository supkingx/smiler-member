package com.smiler.member.tool;

import com.alibaba.fastjson.JSON;
import com.smiler.member.model.vo.UserIndexMessageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/6/28
 */
@Component
public class UserEsMsgSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserEsMsgSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${smiler.user.index.topic}")
    private String smilerUserIndexTopic;

    @Value("${smiler.king.user.index.topic}")
    private String smilerKingUserIndexTopic;

    public void sendMessage(List<BigInteger> ids, Integer type) {
        LOGGER.info("UserEsMsgSender.sendMessage,topic:{},ids:{},type:{}", smilerUserIndexTopic, ids, type);
        kafkaTemplate.send(smilerUserIndexTopic, assembleIndexMessageVo(ids, type));
    }

    public void sendSuperMessage(List<BigInteger> ids, Integer type) {
        LOGGER.info("UserEsMsgSender.sendMessage,topic:{},ids:{},type:{}", smilerKingUserIndexTopic, ids, type);
        kafkaTemplate.send(smilerKingUserIndexTopic, assembleIndexMessageVo(ids, type));
    }

    private static String assembleIndexMessageVo(List<BigInteger> ids, Integer type) {
        UserIndexMessageVo messageVo = new UserIndexMessageVo();
        messageVo.setIds(ids);
        messageVo.setType(type);
        return JSON.toJSONString(messageVo);
    }

}
