package ru.ifmo.lessons.lesson24.synchronization;

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
