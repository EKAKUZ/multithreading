package ru.ifmo.lessons.lesson25_multithcollection.Task1;

import ru.ifmo.lessons.lesson25_multithcollection.Task;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;

public class Application {
    public static void main(String[] args) {
        ArrayBlockingQueue<Order> clientToWaiterOrders =  new ArrayBlockingQueue<>(30);
        ArrayBlockingQueue<Order> cookToClientOrders =  new ArrayBlockingQueue<>(30);
        ArrayBlockingQueue<Order> waiterToCookOrders =  new ArrayBlockingQueue<>(30);


        new Thread(new ClientThread(clientToWaiterOrders, cookToClientOrders)).start();
        new Thread(new ClientThread(clientToWaiterOrders, cookToClientOrders)).start();
        new Thread(new ClientThread(clientToWaiterOrders, cookToClientOrders)).start();
        new Thread(new WaiterThread(clientToWaiterOrders, waiterToCookOrders)).start();
        new Thread(new CookThread(waiterToCookOrders,cookToClientOrders)).start();


    }
}
