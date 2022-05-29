package ru.ifmo.lessons.lesson25_multithcollection.Task1;

import java.util.concurrent.ArrayBlockingQueue;

public class RestaurantClass implements Runnable{
    protected ArrayBlockingQueue<Order> fromOrderds;
    protected ArrayBlockingQueue<Order> toOrderds;

    public RestaurantClass (ArrayBlockingQueue<Order> fromOrderds, ArrayBlockingQueue<Order> toOrderds) {
        this.fromOrderds = fromOrderds;
        this.toOrderds = toOrderds;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Order order = fromOrderds.take();
                toOrderds.put(order);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
