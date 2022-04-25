package ru.ifmo.lessons.lesson23.base;

public class MyTask implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
