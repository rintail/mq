package com.redis.test;

import com.redis.Consumer;
import com.redis.Publisher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class PubSub {
    @Test
    public void pubsub(){
        String channel = "Topic";
        Publisher publisher = new Publisher(channel);
        Consumer consumer = new Consumer(channel);
        while(!consumer.isReady());


        String mesg = "First Mesg !!!";
        String text = null;
        publisher.send(mesg);
        text = consumer.receive();
        assertEquals(mesg, text);

        mesg = "ÖÐÎÄÄØ#@$%";
        publisher.send(mesg);
        text = consumer.receive();
        assertEquals(mesg, text);

        consumer.shutdown();
    }
}
