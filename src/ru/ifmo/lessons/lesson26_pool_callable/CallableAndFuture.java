package ru.ifmo.lessons.lesson26_pool_callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) {
        ExecutorService pool1 = Executors.newFixedThreadPool(3); //пул
        Callable<Message> task = new MessageGenerator(); // задача

        ArrayList<Future<Message>> taskResult = new ArrayList<>(); //результат работы задач

        for (int i = 0; i < 10; i++) {
            // добавляем задачи в очередь на выполнение
            // сообщаем куда нужно передать результат работы потока
            Future<Message> resultContainer = pool1.submit(task);
            // submit передает задачу в очередь и передает ссылку на объект Future<T>
            // Future<T> - результат работы одного потка
            taskResult.add(resultContainer);
         }

        /*for (Future<Message> messageFuture : taskResult) {
            System.out.println("Ожидание получения данных");
            try {
                System.out.println("Сообщение: " + messageFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool1.shutdown();*/
        for (Future<Message> messageFuture : taskResult) {
            System.out.println("Ожидание получения данных");
            try {
                System.out.println("Сообщение: "
                        + messageFuture.get((long)(Math.random() * 2000), TimeUnit.MILLISECONDS));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                System.out.println("Не смог дождаться результата");
            }
        }
        pool1.shutdown(); //ждет результат из контенера заданное количество времени

        ExecutorService pool2 = Executors.newFixedThreadPool(3); //пул на 3 потока
        List<Callable<Message>> tasksList = new ArrayList<>(); //список задач
        tasksList.add(new MessageGenerator());
        tasksList.add(new MessageGenerator());
        tasksList.add(new MessageGenerator());


        try {
            List<Future<Message>> resultContainer = pool2.invokeAll(tasksList); //выполнение списка задач
            pool2.shutdown();
            for (Future<Message> messageFuture : taskResult) {
                System.out.println("Сообщение: " + messageFuture.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // задачу можно отменить future.cancel(true)
        // можно проверить, отменена ли задача future.isCanceled()
        // можно проверить, выполнена задача future.isDone()
    }
}
