package com.biz.qy.chat;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by cool on 2017/7/7.
 */
public class Producer {
    public final static String QUEUE_NAME="biz";
    private final static String Exchage_name = "1919";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connFac = new ConnectionFactory() ;

        //RabbitMQ-Server安装在本机，所以直接用127.0.0.1
        connFac.setHost("127.0.0.1");

        //创建一个连接
        Connection conn = connFac.newConnection() ;

        //创建一个渠道
        Channel channel = conn.createChannel() ;

        channel.exchangeDeclare(Exchage_name,"fanout");
        channel.queueDeclare( Exchage_name , false, false, false, null) ;

        String msg = "Welcome to Rabbit ChatRoom"+"\n";
        String msg1 = "Type q to exit"+"\n";
        String msg2 = "Input your nickname first";
        //发送消息
        channel.basicPublish(Exchage_name,"", null , (msg+msg1+msg2).getBytes());

        System.out.println(msg);
        System.out.println(msg1);
        System.out.println(msg2);

        channel.close();
        conn.close();
    }
}
