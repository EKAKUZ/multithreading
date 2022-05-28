package ru.ifmo.lessons.lesson23.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Interrupting {
    public static void main(String[] args) {
        // фоновый поток, завершает работу, когда
        // завершат работу все НЕ фоновые потоки
        // public void run() {}
        Thread daemon = new Thread(() -> {
            try {
                Thread.sleep(5000);
                Files.write(
                        Paths.get("file.txt"),  // куда записываются данные
                        "собранные данные".getBytes(), // что записывается
                        StandardOpenOption.APPEND); // дозапись
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        daemon.setDaemon(true); // поток становится фоновым
        daemon.start();


        // Прерывание потока:
        // 1. если выброшено необработанное исключение
        // 2. остановилась jvm
        // 3. когда выполнены все инструкции
        // 4. если это фоновый поток и все НЕ фоновые потоки завершили
        // работу

        Thread actions = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){ //interrupted - по умолчанию false
                System.out.println("some action...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { // при исключении снова interrupted = false
                    Thread.currentThread().interrupt(); // поэтому снова устанавливаем true
                }
            }
        });
        actions.start();

        actions.interrupt(); // interrupted = true

    }
}
