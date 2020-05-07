package com.summersky.redis.masterslave;

import redis.clients.jedis.Jedis;

/**
 * @author Lenovo
 * @Authur:zengfanbin
 * @Date:2020-5-7
 * @Time:10:59
 * @Description:主从复制，读写分离，主写从读
 */
public class RedisMS {
    public static void main(String[] args) {
        Jedis jedis_M = new Jedis("127.0.0.1",6379);
        Jedis jedis_S = new Jedis("127.0.0.1",6380);

        jedis_S.slaveof("127.0.0.1",6379);

        jedis_M.set("k6","v6");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(jedis_S.get("k6"));

    }
}
