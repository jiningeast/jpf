package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.util.RedisUtils;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Set;

public class RedisCustomServiceFacadeImpl extends RedisUtils<String , String> implements RedisCustomServiceFacade {

    @Override
    public boolean put(final String keyId, final String entity) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(keyId) ;
                byte[] value = serializer.serialize(entity) ;
                return connection.setNX(key, value);
            }
        });
        return result;
    }

    /**
     * @param keyId
     * @param entity
     * @param liveTime
     */
    @Override
    public void set(final String keyId, final String entity, final long liveTime) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(keyId) ;
                byte[] value = serializer.serialize(entity) ;
                connection.set(key, value);
                if (liveTime > 0) {
                    return connection.expire(key, liveTime);
                }
                return true;
            }
        });
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key) ;
    }

    @Override
    public String get(final String keyId) {
        String entity = redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize(keyId);
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                return serializer.deserialize(value);
            }
        });
        return entity ;
    }

    @Override
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys("*"+pattern+"*") ;
    }

}
