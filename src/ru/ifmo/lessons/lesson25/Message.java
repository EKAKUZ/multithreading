package ru.ifmo.lessons.lesson25;

import java.time.LocalDateTime;

public class Message {
    private String text;
    private LocalDateTime time;

    public Message(String text){
        this.text = text;
        time = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", time=" + time +
                '}';
    }
}
