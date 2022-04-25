package ru.ifmo.lessons.lesson23.base;

import com.sun.source.tree.TryTree;

import java.io.FileWriter;
import java.io.IOException;

import java.util.concurrent.CopyOnWriteArrayList;


public class ThreadFile extends Thread{

    CopyOnWriteArrayList<String> strings;

    public ThreadFile (CopyOnWriteArrayList <String> strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(10000);

                String var = strings.stream().min((str1, str2) -> str1.length()- str2.length()).orElse("");

                FileWriter writer = new FileWriter("file.txt", true);
                System.out.println(var);
                writer.write(var);
                writer.write("\n");
                writer.flush(); //????
                strings.remove(var);
            }
        } catch (InterruptedException |IOException e) {
            e.printStackTrace();
        }


    }
}
