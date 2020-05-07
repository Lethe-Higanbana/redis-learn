package com.summersky.redis.redispool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Lenovo
 * @Authur:zengfanbin
 * @Date:2020-5-7
 * @Time:11:10
 * @Description:连接池
 */
public class RedisPool {
    /**
     * 被volatile修饰的变量不会被本地线程缓存，对该变量的读写都是直接操作共享内存。
     */
    private static volatile JedisPool jedisPool = null;

    private RedisPool() {}

    public static JedisPool getJedisPoolInstance()
    {
        if(null == jedisPool)
        {
            synchronized (RedisPool.class)
            {
                if(null == jedisPool)
                {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100*1000);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig,"127.0.0.1");
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis)
    {
        if(null != jedis)
        {
            //jedisPool.returnResourceObject(jedis);
        }
    }

}
