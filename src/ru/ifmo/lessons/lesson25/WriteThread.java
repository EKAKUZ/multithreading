package ru.ifmo.lessons.lesson25;

import java.util.concurrent.ArrayBlockingQueue;

public class WriteThread implements Runnable {
    private ArrayBlockingQueue<Message> messages;

    public WriteThread(ArrayBlockingQueue<Message> messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        String[] strings = {"сообщение 1", "сообщение 2", "сообщение 3"};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(5000);
                String text = strings[(int)(Math.random() * strings.length)];
                Message message = new Message(text);
                messages.put(message);
                // обьект добавляется в концу очереди
                // если очередь переполнена, поток блокируется до тех
                // пор, пока место в очереди не появится
                System.out.println(message);
                System.out.println("данные добавлены в очередь" + messages);

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
