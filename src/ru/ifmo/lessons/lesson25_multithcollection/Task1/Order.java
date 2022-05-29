package ru.ifmo.lessons.lesson25_multithcollection.Task1;

public class Order {
    private String order;

    public Order(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order='" + order + '\'' +
                '}';
    }
}
