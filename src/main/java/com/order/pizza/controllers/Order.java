package com.order.pizza.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Order {
    String pizzaId;
    String text;

    public Order() {
    }


    public Order(String pizzaId, String text) {
        this.pizzaId = pizzaId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getPizzaId(){
        return pizzaId;

    }


}
