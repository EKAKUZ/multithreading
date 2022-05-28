package ru.ifmo.lessons.lesson23_run_start_join_sleep;

public class MyTask implements Runnable{
    // перечисление всех
    // необходимых свойств, конструкторов и методов

    // инструкции, описанные в методе run
    // должны выполняться в отдельном потоке
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
