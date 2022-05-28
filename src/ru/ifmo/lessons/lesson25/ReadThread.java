package ru.ifmo.lessons.lesson25;

import java.util.concurrent.ArrayBlockingQueue;

public class ReadThread implements Runnable {
    private ArrayBlockingQueue<Message> messages;
    public ReadThread(ArrayBlockingQueue<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Message message = messages.take();
                // возвращает первый элемент из очереди и удаляет его
                // поток блокируется если в очереди нет элементов
                System.out.println("сообщение получено из очереди: " + message);

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
