package com.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * Created by Administrator on 2016/3/7 0007.
 */
public class Sender {
    private static final int SEND_NUMBER = 5;

    // MessageProducer����Ϣ������
    private MessageProducer producer;

    private Connector connector;

    public Sender(){
        connector = new Connector("tcp://172.16.8.16:61616", "TestQueue", Boolean.TRUE);
        init();
    }

    public Sender(Connector con){
        connector = con;
        init();
    }

    private  void init(){
        try {
            producer = connector.createProducer();
            // ���ò��־û����˴�ѧϰ��ʵ�ʸ�����Ŀ����
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void send(String mesg){
        try {
            TextMessage message = connector.session.createTextMessage(mesg);
            producer.send(message);
            connector.session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        connector.shutdown();
    }
}
