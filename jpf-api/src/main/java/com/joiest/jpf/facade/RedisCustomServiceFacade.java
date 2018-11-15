package com.joiest.jpf.facade;

import java.util.Set;

public interface RedisCustomServiceFacade {
    /**
     * 插入
     */
    boolean put(final String keyId, final String entity);
    /**
     * 删除
     */
    void remove(final String keyId);
    /**
     * 查询
     */
    String get(final String keyId);

    Set<String> getKeys(final String pattern) ;

    public void set(final String key, final String value, final long liveTime);

    /**
     * 查询list集合的长度
     * @param keyId
     * @return
     */
    Long getSize(final String keyId);

    /**
     * 向集合中添加数据
     * @param consumerOrderQueue
     * @param toJson
     */
    void lpush(String consumerOrderQueue, String toJson);

    String rPop(String consumerOrderQueue);


}