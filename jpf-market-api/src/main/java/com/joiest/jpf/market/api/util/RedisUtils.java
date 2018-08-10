package com.joiest.jpf.market.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

public abstract class RedisUtils<K extends Serializable, V extends Serializable> {

    @Autowired
    protected RedisTemplate<K , V> redisTemplate;

    public void setRedisTemplate(RedisTemplate<K , V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

}
