package com.xingxue.class11.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;


public class TestRedis {

    @Test
    public void test1(){
        Jedis jedis = new Jedis("localhost");
        //System.out.println(jedis.ping());
        jedis.set("a".getBytes(),"Redis".getBytes());
        try {
            String a = new String(jedis.get("a".getBytes()),"UTF-8");
            System.out.println(a);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        Jedis jedis = new Jedis("localhost");
        //System.out.println(jedis.ping());
        jedis.lpush("b".getBytes(),"Redis".getBytes());
        jedis.lpush("b".getBytes(),"Redis1".getBytes());
        jedis.lpush("b".getBytes(),"Redis2".getBytes());
        List<byte[]> lrange = jedis.lrange("b".getBytes(), 0, 5);
        for (int i = 0; i < lrange.size(); i++) {
            try {
                String s = new String(lrange.get(i),"UTF-8");
                System.out.println(s);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test3(){
        Jedis jedis = new Jedis("localhost");
        //System.out.println(jedis.ping());
        Set<byte[]> keys = jedis.keys("*".getBytes());
    }
}
