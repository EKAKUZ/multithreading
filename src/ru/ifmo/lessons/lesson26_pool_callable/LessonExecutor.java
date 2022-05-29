package ru.ifmo.lessons.lesson26_pool_callable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LessonExecutor extends ThreadPoolExecutor {
// класс может содержать любое количество геттеров сеттеров, свойств, методов, конструкторов, конструкторы могут быть без аргументов
    public LessonExecutor(int corePoolSize, // начальное (оно же минимальное) кол-во потоков
                          int maximumPoolSize, // максимальное кол-во потоков
                          long keepAliveTime, // время как долго избыточные потоки будут простаивать перед удалением
                          TimeUnit unit, // единицы измерения
                          BlockingQueue<Runnable> workQueue //очередь для задач, очереь может создаваться как св-во класса
    ) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    //6ой аргумент  -  как создаются обьекты в пуде
    //7ой аргумент - описывает реакцию пула после shutdown
    // можно переопределить любые доступные методы родителя
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("BEFORE");
    }
}