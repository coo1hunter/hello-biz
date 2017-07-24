package com.biz.qy.chat;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeoutException;

/**
 * Created by cool on 2017/7/7.
 */
public class Customer1 {
    private final static String QUEUE_NAME = "biz1";
    private final static String Exchage_name = "1919";
    private static boolean flag = true;
    private static String NICK_NAME = null;
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connFac = new ConnectionFactory();

        connFac.setHost("127.0.0.1");

        final Connection conn = connFac.newConnection();

        final Channel channel = conn.createChannel();
        channel.exchangeDeclare(Exchage_name,"fanout");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.queueBind(QUEUE_NAME,Exchage_name,"");
        final DefaultConsumer customer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(message);
            }
        };
        channel.basicConsume(QUEUE_NAME,customer);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
        String read1 = null;
        try {
            NICK_NAME = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("q".equals(NICK_NAME)){
            flag = false;
            channel.close();
            conn.close();
        }else {
            System.out.println("hello " + NICK_NAME + ", you can chat from now, enjoy it");
        }
        while (flag) {
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in ));
            //java.io.InputStreamReader继承了Reader类
            try {
                read1 = br1.readLine();
                if ("q".equals(read1)){
                    flag = false;
                    channel.close();
                    conn.close();
                }else {
                    read1 = NICK_NAME + " " + "said" + " " + read1;
                    channel.basicPublish(Exchage_name, "" , null , (read1).getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
