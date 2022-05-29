package ru.ifmo.lessons.lesson27_sem_exchanger;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private volatile boolean prop;

    /*
    * поток 1: prop - false - потоки хранят значение в локальном кеше
    * поток 2: prop - false
    * поток 3: prop - false
    * поток 4: prop - false - заменил prop на true, но другие потоки
    * не знают об изменении значения, так как используют локальный кеш
    *
    * volatitle - говорит, что потоки не кешируют это св-во, и используют актуальное значение
    * */

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1,true);
        //если счетчик permits = 0, поток не получает доступ к ресурсу
        // если > 0 оступ к ресурсу есть
        HashSet<String> commonSet = new HashSet<>();

        Runnable runnable = () -> {
            String thName = Thread.currentThread().getName();
            System.out.println(thName + " ожидает разрешение");
            try {
                sem.acquire(); //уменьшает permits на 1
                Thread.sleep(3000);
                System.out.println(thName + " разрешение получено");
                commonSet.add(thName);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(thName + " освобождает разрешение");
                sem.release(); //permits снова 1
            }


        };
    }
}
