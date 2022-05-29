package ru.ifmo.lessons.lesson25_multithcollection;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {
    public static void main(String[] args) {
        //потокобезопасные коллекции, мапы
        // изначально использовались как многопоточные Vector, HashTable, Stack - сейчас не используются
        // из любой коллекции и мапы можно создать потокобезопасную

        List<ArrayList> list = Collections.singletonList(new ArrayList());

        HashSet<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        Set<String> synchronizedSet = Collections.synchronizedSet(set);

        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());

        // в такой реализации поток полностью блокирует коллекцию и мапу

        //CopyOnWriteArrayList CopyOnWriteArraySet

        //ConcurrentSkipListSet
        //ConcurrentSkipListMap

        //ConcurrentHashMap

        //очереди и блокирующие очереди
        //BlockingDeque
        //BlockingDeque

        LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>();
        //длина очереди по максимальному размеру инта
        strings = new LinkedBlockingQueue<>(30); //длина очереди 30
        // флага справедливости нет

        ArrayBlockingQueue<Message> messages = new ArrayBlockingQueue<>(30);
        messages = new ArrayBlockingQueue<>(30, true);
        // true - первый заблокированный первым получит доступ к очереди,
        // флаг справдливости - ресурсоемкая операция

        new Thread(new WriteThread(messages)).start();
        new Thread(new WriteThread(messages)).start();
        new Thread(new WriteThread(messages)).start();
        new Thread(new ReadThread(messages)).start();

        //блокирующая очередь DelayQueue:
        //1. класс, экземпляры которого помещаются в очередь
        // должен имплементировать интерфейс Delayed
        //2. take блокирует поток, как и в других очередях,
        // также осуществляется проверка - можно ли извлечь элемент из очереди,
        // если нет - блокирует поток, почему элемент нельзя извлечь определяет разработчик


        DelayQueue<Task> tasks = new DelayQueue<>();
        tasks.put(new Task(()->{
            System.out.println("Old Task");
        }, LocalDateTime.now().minusDays(1)));

        tasks.put(new Task(()->{
            System.out.println("Future Task1");
        }, LocalDateTime.now().plusSeconds(20)));

        tasks.put(new Task(()->{
            System.out.println("Future Task2");
        }, LocalDateTime.now().plusMinutes(3)));

        tasks.put(new Task(()->{
            System.out.println("Now Task");
        }, LocalDateTime.now()));
        // метод put вызывает метод compareto

        while (true) {
            Runnable runnable = null;
            try {
                runnable = tasks.take().getAction();
                // метод take определяет можно ли достать поток из очереди,
                // если нет поток блокируется
                new Thread(runnable).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
