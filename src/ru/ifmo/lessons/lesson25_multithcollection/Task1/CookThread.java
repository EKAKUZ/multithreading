package ru.ifmo.lessons.lesson25_multithcollection.Task1;

import java.util.concurrent.ArrayBlockingQueue;

public class CookThread extends RestaurantClass{

    public CookThread(ArrayBlockingQueue<Order> fromOrderds, ArrayBlockingQueue<Order> toOrderds) {
        super(fromOrderds, toOrderds);
    }
}
