package ru.ifmo.lessons.lesson25.Task1;


import ru.ifmo.lessons.lesson25.Message;

import java.util.concurrent.ArrayBlockingQueue;

public class WaiterThread extends RestaurantClass{

    public WaiterThread(ArrayBlockingQueue<Order> fromOrderds, ArrayBlockingQueue<Order> toOrderds) {
        super(fromOrderds, toOrderds);
    }

}
