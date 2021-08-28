package com.order.pizza.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.order.pizza.msgRabbitMQ.RabbitMQConfiguration;


@RestController
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/orders")
    String orderPizza(@RequestBody Order newOrder) {
        var orderId = (int)(Math.random() * 10000);
        String pizzaString = "Pizza with id: "+newOrder.text;

        rabbitTemplate.convertAndSend(RabbitMQConfiguration.topicExchangeName,
                "foo.bar.baz", pizzaString + " ordered with id: " + orderId);

        return "Thanks for your order! Order id:" + orderId;
    }
}