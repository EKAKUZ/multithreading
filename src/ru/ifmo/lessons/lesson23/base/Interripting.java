package ru.ifmo.lessons.lesson23.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Interripting {
    public static void main(String[] args) {
        //фоновый поток, завершает работу, когда завершают работу все не фоновые потоки

        Thread daemon = new Thread(() -> {
            try {
                Thread.sleep(5000);
                Files.write(Paths.get("file.txt"), "собранные данные".getBytes(), StandardOpenOption.APPEND);
            } catch (InterruptedException|IOException e){
                throw new RuntimeException(e);
            }
        });

        daemon.setDaemon(true);
        daemon.start();

        // прерывание потока:
        // 1. если исключение,
        // 2. остановилась jvm,
        // 3. когда выполнены все инструкции
        // 4. если это фоновый и вс не фоновые завершили работу
        // 5.
        // у каждого потока есть параметр interapted по умолчанию false
        Thread actions = new Thread(() ->  {
           while (!Thread.currentThread().isInterrupted()) {
               System.out.println("some action....");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
           }
        });
        actions.start();
        actions.interrupt(); //interrupt = true
        // если метод интерапт вызвать в состоянии ожидания потока,
        // то случится интераптед эксептион и
        // значение интераптед снова поменяется на фол
    }
}
