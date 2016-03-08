package com.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Administrator on 2016/3/8 0008.
 */
public class Connector {

    Session session;
    private Destination destination;
    private Connection connection;

    public Connector(String url, String queue, Boolean autoCreateQueue){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, url);

        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();

            // 获取操作连接
            session = connection.createSession(autoCreateQueue, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(queue);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public MessageConsumer createConsumer() throws JMSException{
        return session.createConsumer(destination);
    }

    public MessageProducer createProducer()throws JMSException{
        return session.createProducer(destination);
    }

    public void shutdown(){
        try {
            session.close();
            connection.stop();
        }catch (JMSException e){
            e.printStackTrace();
        }
    }
}
