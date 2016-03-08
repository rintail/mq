package com.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class Publisher {

    private final Jedis jedis;
    private String channel;
    public Publisher(String channel){
        jedis = new Jedis("172.16.8.16",6379);
        this.channel = channel;
    }

    public void send(String mesg){
        jedis.publish(channel, mesg);
    }
}
