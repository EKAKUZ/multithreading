package ru.ifmo.lessons.lesson24_synchronization_waitnotify.synchronization;

public class Increment extends Thread{
    private SomeAccount account;

    public Increment(SomeAccount account) {
        this.account = account;
    }

    @Override
    public void run(){

        try {
            Thread.sleep(4000);
            // если поток находится в состоянии ожидании при попытки
            // вызвать метод меняющий св-ва потока - будет InterruptedException
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // synchronized - блокирует монитор обьекта из (), в данном случае account
        // в этом блоке не должно быть ничего  не связанного с нужным обьектом
        // для каждого обькта должен вызываться свой synchronized
        // synchronized мб вложенным в другой synchronized
        // последовательность блокировки ресурсов должна быть одинаковой


        synchronized (account) {
            account.upBalance(10);
        }
    }
}
