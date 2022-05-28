package ru.ifmo.lessons.lesson24_synchronization_waitnotify.synchronization;

public class SomeAccount {
    private int balance;

    public int getBalance() {
        return balance;
    }

    public /*synchronized*/ void upBalance(int count){
        // synchronized метод блокирует монитор обьекта, у которого вызывается данный метод
        balance += count;
    }
}
