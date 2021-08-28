package com.order.pizza.controllers;

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
