package ru.ifmo.lessons.lesson25_multithcollection.Task1;


import java.util.concurrent.ArrayBlockingQueue;

public class WaiterThread extends RestaurantClass{

    public WaiterThread(ArrayBlockingQueue<Order> fromOrderds, ArrayBlockingQueue<Order> toOrderds) {
        super(fromOrderds, toOrderds);
    }

}
