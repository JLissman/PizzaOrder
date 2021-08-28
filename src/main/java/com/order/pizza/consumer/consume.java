package com.order.pizza.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class consume {
    public static void main(String[] args) throws IOException, TimeoutException {
    ConnectionFactory factory = new ConnectionFactory();


    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    String queueName = "pizza-orders";
    channel.queueDeclare(queueName,false, false, false, null);


    System.out.println("Listening to queue "+queueName);
    channel.basicConsume(queueName,true,(consumerTag, message)-> {
                String m = new String(message.getBody(),"UTF-8");
                System.out.println(m);

            },
            consumerTag-> {});


    }




}
