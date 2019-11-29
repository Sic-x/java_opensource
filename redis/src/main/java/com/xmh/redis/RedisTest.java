package com.xmh.redis;

import com.xmh.utils.JedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.Map;

public class RedisTest {

    @Test
    public void test() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("key","这是个value");
        System.out.println(jedis.get("key"));
        jedis.close();
    }


    @Test
    public void testString() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("students","学生");
        System.out.println(jedis.get("students"));
        jedis.mset("student1","勇勇","student2","浚浚","student3","伟伟");
        System.out.println(jedis.mget("student1", "student2", "student3"));
        jedis.set("age","12");
        jedis.incr("age");
        System.out.println(jedis.get("age"));
        jedis.decr("age");
        System.out.println(jedis.get("age"));
        jedis.decrBy("age",5);
        System.out.println(jedis.get("age"));
        jedis.incrBy("age",10);
        System.out.println(jedis.get("age"));
        jedis.close();
    }


    @Test
    public void testCommon() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        System.out.println(jedis.keys("*"));
        jedis.del("key");
        System.out.println(jedis.keys("*"));
        jedis.expire("age",30);
        System.out.println(jedis.ttl("age"));
        jedis.flushDB();
        jedis.flushAll();
        jedis.close();
    }


    @Test
    public void testList() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        System.out.println(jedis.keys("*"));
        jedis.lpush("student4","jj","yy","ww");
        jedis.rpush("student4","mm");
        jedis.lrange("student4",0,-1).forEach(e-> System.out.println(e));
        jedis.lpop("student4");
        jedis.rpop("student4");
        //根据count值移除列表key中与参数 value 相等的元素
        // count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
        // count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
        // count = 0 : 移除表中所有与 value 相等的值。
        jedis.lrem("student4",1,"jj");
        //返回列表 key 中，下标为 index 的元素
        jedis.lindex("student4",2);
        //只留范围内的值
        jedis.ltrim("student4",1,2);
        jedis.close();
    }


    @Test
    public void testSet() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        jedis.flushAll();
        System.out.println(jedis.keys("*"));
        jedis.sadd("student","name","age","habit");
        System.out.println(jedis.smembers("student"));
        jedis.srem("student","name","age");
        jedis.close();
    }


    @Test
    public void testSortSet() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        jedis.zadd("student1",5,"勇勇");
        jedis.zadd("student1",15,"浚浚");
        jedis.zadd("student1",20,"明明");
        jedis.zadd("student1",25,"伟伟");
        System.out.println(jedis.zrange("student1", 0, -1));
        jedis.zrem("student1","浚浚");
        jedis.zcount("student1",15,25);
        jedis.zincrby("student1",13,"伟伟");
        jedis.zrevrangeByScore("student1",3,33);
        jedis.close();
    }


    @Test
    public void testHash() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        jedis.flushAll();
        jedis.hset("students","student1","浚浚");
        jedis.hset("students","student2","伟伟");
        jedis.hset("students","student3","勇勇");
        jedis.hset("students","student4","天天");
        jedis.hset("students","student5","明明");
        jedis.hget("students","student1");
        Map<String, String> map = new HashMap<>();
        map.put("student6","涛涛");
        map.put("student7","东东");
        map.put("student8","坤坤");
        map.put("student9","佳佳");
        jedis.hmset("students",map);
        jedis.hmget("students","student7","student9");
        System.out.println(jedis.hkeys("students"));
        System.out.println(jedis.hvals("students"));
        System.out.println(jedis.hgetAll("students"));
        jedis.close();
    }


    @Test
    public void testTransaction() throws Exception {
        Jedis jedis = JedisUtil.getJedis();
        jedis.flushAll();
        Transaction multi = jedis.multi();
        multi.sadd("student1","佳佳");
        multi.exec();
        System.out.println(jedis.keys("*"));
        Transaction multi2 = jedis.multi();
        multi2.sadd("student2","伟伟");
        multi2.discard();
        System.out.println(jedis.keys("*"));
    }


}
