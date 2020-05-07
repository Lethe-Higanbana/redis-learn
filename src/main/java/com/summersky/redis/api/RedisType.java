package com.summersky.redis.api;

import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author Lenovo
 * @Authur:zengfanbin
 * @Date:2020-5-7
 * @Time:9:45
 * @Description:Redis常用数据类型
 */
public class RedisType {
    public static void main(String[] args) {
        // 创建一个Jedis对象，连接redis，redis的IP地址跟端口号
        Jedis jedis = new Jedis("192.168.111.128",6379);
        // 清空当前数据库数据,清空所有数据库数据：jedis.flushAll();
        jedis.flushDB();

        // redis的String类型，单个
        jedis.set("string类型","hello");
        // 批量
        jedis.mset("k1","v1","k2","v2","k3","v3");
        System.out.println(jedis.mget("k1","k2","k3"));

        // list类型
        jedis.lpush("list类型","v1","v2","v3");
        // 转换成list
        List<String> list = jedis.lrange("list类型",0,-1);
        for (String value:list){
            System.out.println(value);
        }

        // set类型
        jedis.sadd("orders","jd001");
        jedis.sadd("orders","jd002");
        jedis.sadd("orders","jd003");
        Set<String> set1 = jedis.smembers("orders");
        for (Iterator iterator = set1.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            System.out.println(string);
        }

        // hash类型
        jedis.hset("hash1","userName","lisi");
        System.out.println(jedis.hget("hash1","userName"));
        Map<String,String> map = new HashMap<String,String>();
        map.put("telphone","13811814763");
        map.put("address","atguigu");
        map.put("email","abc@163.com");
        jedis.hmset("hash2",map);
        List<String> result = jedis.hmget("hash2", "telphone","email");
        for (String element : result) {
            System.out.println(element);
        }

        // Zset类型
        jedis.zadd("zset01",60d,"v1");
        jedis.zadd("zset01",70d,"v2");
        jedis.zadd("zset01",80d,"v3");
        jedis.zadd("zset01",90d,"v4");

        Set<String> s1 = jedis.zrange("zset01",0,-1);
        for (Iterator iterator = s1.iterator(); iterator.hasNext();) {
            String string = (String) iterator.next();
            System.out.println(string);
        }
    }
}
