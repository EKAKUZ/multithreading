package ru.ifmo.lessons.lesson23.base;

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class JoinThreads {
    public static void main (String[] args) {

        CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();

        Thread task1 = new Thread(()->{

            try {
                // приостанавливает работу через 5000 мс или больше, когда планировщик потоков решит запустить поток
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task1 обработал данные");
            integers.add(5000);
        });



        Thread task2 = new Thread(()->{

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("task2 обработал данные");
            integers.add(3000);
        });


        Thread task3 = new Thread(()->{
            Scanner in = new Scanner(System.in);
            System.out.println("Введите данные");
            int userNum = in.nextInt();

            System.out.println("task3 обработал данные");
            integers.add(userNum);
        });

        task1.start();
        task2.start();
        task3.start();
        //System.out.println("main " + integers); -  список мб пустой, так main не ждет остальные потоки

        // основной поток должен ждать завершения других потоков
        try {
            task1.join();
            task2.join();
            task3.join(100000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("main " + integers);


    }
}
