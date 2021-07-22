package com.smiler.member.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 消费端配置
 * @Author: wangchao
 * @Date: 2021/7/21
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private static final int DEFAULT_POLL_TIMEOUT = 2000;

    @Value("${kafka.consumer.bootstrap.servers}")
    private String servers;

    @Value("${kafka.consumer.enable.auto.commit}")
    private boolean autoCommit;

    /**
     * 自动提交时间间隔
     */
    @Value("${kafka.consumer.auto.commit.interval.ms}")
    private String autoCommitInterval;

    /**
     * 超过该时间没有收到心跳，就认为当前消费者失效
     */
    @Value("${kafka.consumer.session.timeout}")
    private int sessionTimeOut;

    /**
     * 超过该时间没有poll，则认为该消费者失效
     */
    @Value("${kafka.consumer.max.poll.interval.ms}")
    private int maxPollInterval;

    @Value("${kafka.consumer.group}")
    private String group;

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        return new KafkaConsumer<>(ConsumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        //  机器数量*concurrency <= 分区数
        factory.setConcurrency(1);
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setPollTimeout(DEFAULT_POLL_TIMEOUT);
        return factory;
    }

    private ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(ConsumerConfigs());
    }

    private Map<String, Object> ConsumerConfigs() {
        Map<String, Object> kafkaPropMap = new HashMap<>();
        kafkaPropMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        kafkaPropMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        kafkaPropMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        kafkaPropMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeOut);
        kafkaPropMap.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, maxPollInterval);
        kafkaPropMap.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        // 该序列化方式和发送端保持一致
        kafkaPropMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        kafkaPropMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return kafkaPropMap;
    }

}
