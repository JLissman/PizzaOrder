package com.order.pizza.consumer;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
