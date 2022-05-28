package ru.ifmo.lessons.lesson24_synchronization_waitnotify.synchronization;


import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        SomeAccount account = new SomeAccount();

        ArrayList<Increment> increments = new ArrayList<>();
        for (int i=0; i<100; i++) {
            increments.add(new Increment(account));
        }

        for (Increment increment : increments) {
            increment.start();
        }

        for (Increment increment: increments) {
            try {
                increment.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Balance: " + account.getBalance());
    }
}
