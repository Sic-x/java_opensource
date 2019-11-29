package com.xmh.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {

    //连接池对象
    private static JedisPool pool = null;

    static {
        //1 创建jedispool配置对象
        JedisPoolConfig config = new JedisPoolConfig();

        //2 做配置-四个
        config.setMaxIdle(2);
        config.setMaxTotal(10);
        config.setMaxWaitMillis(1*1000); //创建连接超时
        config.setTestOnBorrow(true);//获取连接是测试连接是否畅通
        //3 创建jedispool
        //1*1000 获取连接超时时间
        pool = new JedisPool(config,"127.0.0.1",6379,1*1000,"123456");
    }

    //获取连接对象
    public static Jedis getJedis(){
        return pool.getResource();
    }

}
