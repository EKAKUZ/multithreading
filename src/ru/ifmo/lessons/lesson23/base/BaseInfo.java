package ru.ifmo.lessons.lesson23.base;

//git branch - список веток
//git branch имя ветки - создать ветку
//git checkout имя ветки - переключается на ветку

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class BaseInfo {
    public static void main(String[] args) {
        //main - основной поток выполнения программы
        //запуск программы ->  создание процесса ОС -> начинается последовательное выполнение инструкций


        // после создания основного потока можно запускать
        // дополнительные потоки, тогда инструкции процесса
        // будут выполняться параллельно
        // одно ядро процессора может обрабатывать один поток

        // жизненный цикл потока:
        // 1. NEW - поток создан (создан экземпляр класса Thread)
        // 2. RUNNABLE - управление потоком передается Thread Scheduler -
        // 'планировщику потоков jvm' (вызван метод start у экземпляра класса Thread)
        // 3. RUNNING - поток запущен планировщиком и начинает выполнять инструкции,
        // время запуска потока определяет сам 'планировщик потоков'
        // 4. NON-RUNNING (TIME WAITING, WAITING, BLOCKED) - поток может
        // находиться в состоянии ожидания
        // 5. TERMINATED - поток завершает работу

        // Варианты описания ИНСТРУКЦИЙ потока:
        // 1. создать класс, который наследуется от класса Thread,
        // инструкции, которые должен выполнять поток описываются в методе
        // public void run();
        MyThread myThread = new MyThread();
        myThread.setName("my Thread 1"); // установить группу, установить имя, попытаться установить приоритет

        // передача потока планировщику (Thread Scheduler )
        myThread.start();
        //myThread.run(); // без передачи потока планировщику, инструкции этого метода не будут выполняться паралельно


        // 2. инструкции, которые должен выполнять поток описываются в методе
        // public void run() интерфейса Runnable (при этом набор инструкций
        // можно описать в лямбда или создать отдельный класс)

        Thread thread = new Thread(new MyTask());
        //MyTask mytask = new MyTask(); -  не относится к потокам
        thread.setName("my Thread 2");
        thread.start();

        //Runnable -  функциональный интерфейс
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();

        // 3. воспользоваться возможностями пакета java.util.concurrent.*

        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();


        ThreadScanner scanner = new ThreadScanner(strings);
        ThreadFile threadFile = new ThreadFile(strings);
        scanner.start();
        threadFile.start();

    }
}