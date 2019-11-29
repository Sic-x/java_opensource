package com.xmh.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ClusterTest {

    @Test
    public void testCluster() throws IOException, InterruptedException {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        nodes.add(new HostAndPort("127.0.0.1", 6380));
        nodes.add(new HostAndPort("127.0.0.1", 6381));
        nodes.add(new HostAndPort("127.0.0.1", 6382));
        nodes.add(new HostAndPort("127.0.0.1", 6383));
        nodes.add(new HostAndPort("127.0.0.1", 6384));
        JedisCluster cluster = new JedisCluster(nodes);
        try {
            String res = cluster.get("name");
            System.out.println(res);
//            cluster.quit();
        } catch (Exception e) {
            e.printStackTrace();
//            cluster.quit();
        }
    }
}


