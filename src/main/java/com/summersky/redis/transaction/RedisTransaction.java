package com.summersky.redis.transaction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

/**
 * @author Lenovo
 * @Authur:zengfanbin
 * @Date:2020-5-7
 * @Time:10:12
 * @Description:redis的事务，正常操作
 */
public class RedisTransaction {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //监控key，如果该动了事务就被放弃
        //被当作一个命令进行执行,multi()开启事务块
        Transaction transaction = jedis.multi();
        Response<String> response = transaction.get("serialNum");
        transaction.set("serialNum","s002");
        response = transaction.get("serialNum");
        transaction.lpush("list3","a");
        transaction.lpush("list3","b");
        transaction.lpush("list3","c");

        transaction.exec();
        //2 transaction.discard();
        System.out.println("serialNum***********"+response.get());
    }
}
