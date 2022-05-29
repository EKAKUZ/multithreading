package ru.ifmo.lessons.lesson27_sem_exchanger;

import java.util.concurrent.Exchanger;

import static java.lang.Thread.sleep;

public class ExchangerExample {
    public static void main(String[] args) {
        // класс Exchanger используется для передачи данных между потоками
        // обмен осуществляется с помощью метода exchange,
        // при этом обмен производится если оба потока положили данные в exchange
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                System.out.println("Первый поток получил данные: " +
                        exchanger.exchange("Первый поток"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                sleep(3000);
                System.out.println("Второй поток получил данные: " +
                        exchanger.exchange("Второй поток"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
