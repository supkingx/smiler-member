package com.smiler.member.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.SetParams;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/5
 */
public class RedisUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

    private static final String OK = "OK";

    /**
     * true:有锁
     * false:无锁
     *
     * @param redisTemplate
     * @param cacheKey
     * @param timeout
     * @return
     */
    public static boolean redisLock(StringRedisTemplate redisTemplate, String cacheKey, int timeout) {
        if (OK.equals(setNXSilent(redisTemplate, cacheKey, timeout))) {
            return false;
        } else {
            return true;
        }
    }

    public static void unLock(StringRedisTemplate redisTemplate, String cacheKey) {
        deleteCache(redisTemplate, cacheKey);
    }

    public static String setNXSilent(StringRedisTemplate redisTemplate, String cacheKey, int timeout) {
        try {
            return redisTemplate.execute((RedisCallback<String>) redisConnection -> {
                JedisCommands commands = (JedisCommands) redisConnection.getNativeConnection();
                return commands.set(cacheKey, "user", SetParams.setParams().nx().ex(timeout));
            });
        } catch (Exception e) {
            LOGGER.error("SET If Not Exists error!,key:{}", cacheKey);
            throw new RuntimeException(e);
        }
    }

    public static void deleteCache(StringRedisTemplate redisTemplate, String cacheKey) {
        redisTemplate.delete(cacheKey);
    }
}
