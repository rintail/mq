package com.activemq.test;

import com.activemq.Connector;
import com.activemq.Consumer;
import com.activemq.Sender;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class PubSub {

    private Connector connector;

    @Before
    public void setup(){
        connector = new Connector("tcp://172.16.8.16:61616", "TestQueue", Boolean.TRUE);
    }

    @After
    public void destroy(){
        connector.shutdown();
    }

    @Test
    public void pubsub(){
        Sender sender = new Sender();
        Consumer consumer = new Consumer();

        String message = "First Message !!!";
        sender.send(message);
        String text = consumer.receive();
        assertEquals(message, text);

        message = "中文情况$#";
        sender.send(message);
        text = consumer.receive();
        assertEquals(message, text);

        consumer.stop();
        sender.stop();
    }

/*
    @Test
    public void shareConnector(){
        Sender sender = new Sender(connector);
        Consumer consumer = new Consumer(connector);

        String text = null;
        String message = "WTF !!!";
        sender.send(message);
        text = consumer.receive();
        assertEquals(message, text);
        System.out.println(message + " : " + text);

        message = "348w43#@^&($#";
        sender.send(message);
        text = consumer.receive();
        assertEquals(message, text);
        System.out.println(message +" : " + text);
    }


    @Test
    public void testNull(){
        Connector connector = new Connector("tcp://172.16.8.16:61616", "TestQueue", Boolean.TRUE);

        Sender sender = new Sender(connector);
        Consumer consumer = new Consumer(connector);

        String message = null;
        sender.send(message);
        String text = consumer.receive();

        assertEquals(message, text);
    }
*/
}
