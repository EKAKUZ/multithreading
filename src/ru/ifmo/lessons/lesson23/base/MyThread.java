package ru.ifmo.lessons.lesson23.base;


public class MyThread extends Thread{
    // в данном классе могут быть любые методы, конструкторы и свойства

    // инструкции, описанные в методе run будут выполняться в отдельном потоке

    @Override
    public void run() {
        System.out.println(this.getName());
        System.out.println(Thread.currentThread().getName());

    }
}
