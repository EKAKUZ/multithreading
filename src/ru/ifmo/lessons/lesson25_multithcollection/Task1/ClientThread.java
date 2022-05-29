package ru.ifmo.lessons.lesson25_multithcollection.Task1;

import java.util.concurrent.ArrayBlockingQueue;

public class ClientThread extends RestaurantClass{
    public ClientThread(ArrayBlockingQueue<Order> fromOrderds, ArrayBlockingQueue<Order> toOrderds) {
        super(fromOrderds, toOrderds);
    }

    @Override
    public void run() {
        String[] strings = {"order1", "order2", "order3"};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(5000);
                String text = strings[(int)(Math.random() * strings.length)];
                Order order = new Order(text);
                fromOrderds.put(order);
                System.out.println("клиент сделал заказ:" + order);
                Order readorder = toOrderds.take();
                System.out.println("клиент получил заказ: " + readorder);

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
