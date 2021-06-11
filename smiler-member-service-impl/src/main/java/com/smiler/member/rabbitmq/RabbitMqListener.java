package com.smiler.member.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/26
 */
@Service
public class RabbitMqListener {

    //    @RabbitListener(queues = "smiler.user.index")
    public void receiveMessage(Message message, String content) {
        System.out.println("接收到消息。。。。内容:" + message + "\n===>类型:" + message.getClass());
        System.out.println("===>body:" + Arrays.toString(message.getBody()));
        MessageProperties messageProperties = message.getMessageProperties();
        System.out.println("===>messageProperties:" + JSON.toJSONString(messageProperties));
        System.out.println("===>content:" + content);
    }
}
