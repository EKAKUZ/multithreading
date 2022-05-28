package ru.ifmo.lessons.lesson24_synchronization_waitnotify.waitnotify;

public class Application {
    public static void main(String[] args) {
        Library storage = new Library();


        new Thread(new PutThread(storage)).start();
        new Thread(new GetThread(storage)).start();
        new Thread(new PutThread(storage)).start();
        new Thread(new GetThread(storage)).start();
    }
}