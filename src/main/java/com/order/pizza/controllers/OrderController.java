package com.order.pizza.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.order.pizza.msgRabbitMQ.RabbitMQConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/orders")
    String orderPizza(@RequestBody Order newOrder) throws IOException {
        var orderId = (int)(Math.random() * 10000);
        String pizzaString = getPizza(newOrder.getPizzaId());
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName,
                "foo.bar.baz", pizzaString + "\nOrder specific notes:"+ newOrder.getText() +"\nOrder ID:" + orderId );

        return "Thanks for your order! Order id:" + orderId;
    }

    private static String getPizza(String id) throws IOException {
        URL url = new URL("http://localhost:8080/pizzas/"+id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        if(status == 200){
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        }else{
            return "ERROR MOTHAFAKKA";

        }

    }
}