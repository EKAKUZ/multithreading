package ru.ifmo.lessons.lesson23.base;

// git branch - список веток
// git branch имя_ветки - создать ветку
// git checkout имя_ветки - переключиться на ветку

import java.util.concurrent.CopyOnWriteArrayList;

public class BaseInfo {
    public static void main(String[] args) {
        // запуск программы -> создание процесса ОС
        // -> создается основной поток (main поток)
        // -> начинается последовательное выполнение инструкций

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
        // 5. TERMINATED - поток завершил работу

        // Варианты описания ИНСТРУКЦИЙ потока:
        // 1. создать класс, который наследуется от класса Thread,
        // инструкции, которые должен выполнять поток описываются в методе
        // public void run();
        // 2. инструкции, которые должен выполнять поток описываются в методе
        // public void run() интерфейса Runnable (при этом набор инструкций
        // можно описать в лямбда или создать отдельный класс)
        // 3. воспользоваться возможностями пакета java.util.concurrent.*

        MyThread myThread1 = new MyThread();
        myThread1.setName("myThread 1");

        myThread1.start(); // передача потока планировщику (Thread Scheduler)
        // myThread1.run();

        MyThread myThread2 = new MyThread();
        System.out.println(myThread2.getName());
        myThread2.setName("myThread 2");

        myThread2.start();

        MyTask myTask1 = new MyTask(); // объект не является потоком
        Thread thread = new Thread(myTask1); // Runnable
        thread.setName("myTask 1");
        thread.start();

        // Runnable - функциональный интерфейс
        // поэтому инструкции потка можно описать через
        // lambda выражение
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();


        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        // один поток получает строки от пользователя и добавляет их
        // в strings

        // Thread.sleep(млс);
        // другой поток приостанавливает работу на 30 секунд,
        // после чего записывает в файл минимальную
        // по размеру строку из strings - используем Stream API
        // после чего строку необходимо удалить из коллекции

        // лямбда выражения для реализации метода run не используем

    }
}