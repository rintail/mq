package com.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class Consumer {

    private Connector connector;
    MessageConsumer consumer;
    public Consumer(){
        connector = new Connector("tcp://172.16.8.16:61616", "TestQueue", Boolean.FALSE);
        init();
    }

    public Consumer(Connector conn){
        connector = conn;
        init();
    }
    private void init(){
        try{
            consumer = connector.createConsumer();
        }catch(JMSException e){
            e.printStackTrace();
        }
    }

    public String receive(){
        try {
            TextMessage mesg = (TextMessage)consumer.receive(4 * 1000);
            return mesg.getText();
        } catch (JMSException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stop(){
        connector.shutdown();
    }
}
