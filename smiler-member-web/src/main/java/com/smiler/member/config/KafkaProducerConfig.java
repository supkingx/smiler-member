package com.smiler.member.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: kafka生产者配置
 * @Author: wangchao
 * @Date: 2021/7/21
 */
@Lazy
@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.producer.bootstrap.servers}")
    private String servers;

    @Value("${kafka.producer.retries}")
    private int retries;

    /**
     * 当批次被填满，批次里的所有消息会被发送出去。不过生产者并不一定都会等到批次被填满了才发送，半满批次，
     * 甚至只包含一个消息的批次也可以发送。所以就算把`batch.size`设置的很大，也不会造成延迟，只会占用更多的内存而已，
     * 如果设置的过小，生产者会因为频繁发送消息而增加一些额外的开销。
     * 单位：字节
     */
    @Value("${kafka.producer.batch.size}")
    private int batchSize;

    /**
     * 多久发送一次消息
     */
    @Value("${kafka.producer.linger.ms}")
    private int linger;

    /**
     * 本地消息缓存
     */
    @Value("${kafka.producer.buffer.memory}")
    private int bufferMemory;

    /**
     * 一次请求最大参数大小
     */
    @Value("${kafka.producer.max.request.size}")
    private int maxRequestSize;

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>( producerConfigs());
        return new KafkaTemplate<>(producerFactory);
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> kafkaPropMap = new HashMap<>();
        kafkaPropMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        kafkaPropMap.put(ProducerConfig.RETRIES_CONFIG, retries);
        kafkaPropMap.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        kafkaPropMap.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        kafkaPropMap.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        kafkaPropMap.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, maxRequestSize);
        // 设置kafka的序列化器，需要去消费端设置对应的反序列化器
        kafkaPropMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaPropMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return kafkaPropMap;
    }
}
