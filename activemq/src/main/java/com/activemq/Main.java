package com.activemq;

import javax.jms.Message;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class Main {
    public static void main(String[] args) throws  Exception{
        Consumer consumer = new Consumer();

        for(int i = 0; i < 10 ; ++i) {
            String str = consumer.receive();
            System.out.println(str);
        }
    }
}
