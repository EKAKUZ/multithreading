package ru.ifmo.lessons.lesson26_pool_callable;

import java.util.List;
import java.util.concurrent.*;

public class TaskExecutor {
    public static void main(String[] args) {
        // пул потоков  - реализация порождающего паттерна проектирования - обьектный пул
        // пул потоков: thread1, thread2, thread3
        // очередь задачь task1, task2, task3, task4, task5
        //пулу потоков передаются задачи в очереди и свободные потоки выполняют задачи

        // пул потоков можно реальзовать самостоятельно или воспользоваться готовым
        // 1. фиксированного размера (колличество потоков указывается при создании и не меняется)
        // 2. гибкого размера - колличество потоков коллеблится от min до max
        // (min и max указываются при создании)
        // 3. пул для выполнения задач с указанным интервалом
        // 4. можно расширить существующий класс (пул потоков), отнаследовавшись от него
        // 5. можно имплиментровать интерфейс пула потоков, и реализовать свой класс
        // разный по типу задачи (математические, работа с файлами) принято разделять на разные пулы потоков

        // в пуле потоков не принято помещать бесконечные циклы, тогда лучше создавать отдельный поток
        // следить за количеством созданных пулом потоков - перерасход ресурсов
        // при собственной реализации, мб утечка пулов, если нет реализации возвращения потоков в пул

        ExecutorService fixedPool = Executors.newFixedThreadPool(2); // создание пула на 2 потока
        for (int i = 0; i < 7 ; i++) {
            int iValue = i;
            fixedPool.execute(() ->{
                System.out.println("Выполнение задачи № " + iValue);
            });
        }

        //fixedPool.shutdown();
        // метод завершает текущие задачи и не принимает новые
        // к пулу уже нельзя обратиться
        // shutdown не завершает потоки с бесконечными циклами и ожиданием ввода от пользователя

        //shutdownNow();
        // прерывает выполнение задач, возвращает список с невыполнеными задачами и не принимает новые,
        // к пулу нельзя обратиться повторно
        // shutdownNow скорее всего не завершит бесконечную задачу
        List<Runnable> runnables = fixedPool.shutdownNow();
        System.out.println(runnables); //runnables - список не выполненых задач

        // пул на 1 поток
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        singlePool.execute(() ->{
            System.out.println("Task 1");
        });
        singlePool.execute(() ->{
            System.out.println("Task 2");
        });
        singlePool.shutdown();

        //пул выполняющий задачи с заданным расписанием -  на один поток
        ScheduledExecutorService everySevenSecond = Executors.newSingleThreadScheduledExecutor();
        // пул потоков связанных со временем
        everySevenSecond.scheduleAtFixedRate(
                // метод не берет в расчет время выполнения задачи
                () -> { // задача, которая выполняется
                    System.out.println("scheduleAtFixedRate Task1");
                },
                0, //первоначальное время ожидания перед началом выполнения
                7, // задача запускается на выполнение каждые 7
                TimeUnit.SECONDS // секунд
        );
        // время выполнения задачи, должно быть меньше интервала запуска потока

        ScheduledExecutorService everyThreeSecond = Executors.newSingleThreadScheduledExecutor();
        everyThreeSecond.scheduleWithFixedDelay(
                // метод берет в расчет время выполнения задачи
                () -> { // задача, которая выполняется
                    System.out.println("scheduleAtFixedRate Task2");
                },
                0, //первоначальное время ожидания перед началом выполнения
                3, // задача запускается на выполнение каждые 3
                TimeUnit.SECONDS // секунды, после завершения предыдущей задачи
        );
        // можно завершить вызовом метода shutdown

        //отложенное выполнение
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(
                () -> {
                    System.out.println("scheduledExecutor task");
                },
                10,
                TimeUnit.SECONDS
        );
        scheduledExecutor.shutdown();

        ExecutorService lessonPool = new LessonExecutor(
                2,
                7,
                20,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        lessonPool.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("lessonPool 1");
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        lessonPool.execute(() -> {
                System.out.println("lessonPool 2");
        });
        lessonPool.shutdown();
    }
}