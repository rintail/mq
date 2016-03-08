package com.redis;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class Consumer {
    private ArrayBlockingQueue<String> kMesgQueue = new ArrayBlockingQueue<String>(4);


    private Subscriber mSubscriber;
    public Consumer(final String channel){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mSubscriber = new Subscriber(channel);
                mSubscriber.subscribe();
            }
        }).start();
    }

    public String receive(){
        try {
            return kMesgQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void shutdown(){
        mSubscriber.unsubscribe();
    }

    public boolean isReady(){
        return  mIsConsumerReady;
    }

    private volatile boolean mIsConsumerReady = false;
    private class Subscriber extends JedisPubSub{
        private String channel;
        private Client client;
        public Subscriber(String channel){
            Jedis jedis = new Jedis("172.16.8.16",6379, 0);
            client = jedis.getClient();
            this.channel = channel;
        }

        void subscribe(){
            proceed(client, channel);
        }

        public void onMessage(String channel, String message) {
            try {
                kMesgQueue.put(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void onSubscribe(String channel, int subscribedChannels) {
            System.out.println("subscribed " + channel);
            mIsConsumerReady = true;
        }
    }

}
