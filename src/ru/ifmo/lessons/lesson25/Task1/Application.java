package ru.ifmo.lessons.lesson25.Task1;

import ru.ifmo.lessons.lesson25.ReadThread;
import ru.ifmo.lessons.lesson25.WriteThread;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {
    public static void main(String[] args) {
        ArrayBlockingQueue<Order> clientToWaiterOrders =  new ArrayBlockingQueue<>(30);
        ArrayBlockingQueue<Order> cookToClientOrders =  new ArrayBlockingQueue<>(30);
        ArrayBlockingQueue<Order> waiterToCookOrders =  new ArrayBlockingQueue<>(30);


/*        new Thread(new ClientThread(clientToWaiterOrders, cookToClientOrders)).start();
        new Thread(new ClientThread(clientToWaiterOrders, cookToClientOrders)).start();
        new Thread(new ClientThread(clientToWaiterOrders, cookToClientOrders)).start();
        new Thread(new WaiterThread(clientToWaiterOrders, waiterToCookOrders)).start();
        new Thread(new CookThread(waiterToCookOrders,cookToClientOrders)).start();*/


        DelayQueue<Task> tasks = new DelayQueue<>();
        tasks.put(new Task(()->{
            System.out.println("Old Task");
        }, LocalDateTime.now().minusDays(1)));

        tasks.put(new Task(()->{
            System.out.println("Future Task");
        }, LocalDateTime.now().plusSeconds(20)));

        tasks.put(new Task(()->{
            System.out.println("Now Task");
        }, LocalDateTime.now()));

        while (true) {
            Runnable runnable = null;
            try {
                runnable = tasks.take().getAction();
                new Thread(runnable).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
