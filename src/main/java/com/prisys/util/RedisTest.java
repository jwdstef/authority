package com.prisys.util;

import redis.clients.jedis.Jedis;

public class RedisTest {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.1.10", 6379);
        System.out.println(jedis.get("foo"));
        System.out.println("Connected to Redis");

	}

}
