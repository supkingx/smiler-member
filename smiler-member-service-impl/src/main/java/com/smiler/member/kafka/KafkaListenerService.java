package com.smiler.member.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @description: 测试接收
 * @Author: wangchao
 * @Date: 2021/6/27
 */
//@Configuration
public class KafkaListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);

    private static final String TOPIC = "smiler.user.index";
    private static final String GROUP_ID = "smiler.user";

    @KafkaListener(id = "1", topics = TOPIC, groupId = GROUP_ID)
    public void listener(String msg) {
        LOGGER.info("收到消息了:{}", msg);
    }
}
