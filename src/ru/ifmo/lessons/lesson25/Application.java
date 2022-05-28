package ru.ifmo.lessons.lesson25;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {
    public static void main(String[] args) {
        //потокобезопасные коллекции, мапы
        // Vector, HashTable, Stack - сейчас не используются
        // из любой коллекции и мапы можно создать потокобезопасную
        HashSet<String> set = new HashSet<>();
        List<ArrayList> list = Collections.singletonList(new ArrayList());
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
        // true - первый заблокированный первым получит доступ к очереди, очень энергоемкая операция

        new Thread(new WriteThread(messages)).start();
        new Thread(new WriteThread(messages)).start();
        new Thread(new WriteThread(messages)).start();
        new Thread(new ReadThread(messages)).start();

        //блокирующая очередь DelayQueue:
        //1. класс, экземпляры которого помещаются в очередь
        // должен имплементировать интерфейс DelayQueue
        //2. take блокирует поток
    }

}
