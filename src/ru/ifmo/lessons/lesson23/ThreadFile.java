package ru.ifmo.lessons.lesson23;

import com.sun.source.tree.TryTree;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CopyOnWriteArrayList;


public class ThreadFile extends Thread{

    CopyOnWriteArrayList<String> strings;

    public ThreadFile (CopyOnWriteArrayList <String> strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);

                String var = strings.stream().min((str1, str2) -> str1.length() - str2.length()).orElse("");
                Files.write(Paths.get("file.txt"), var.getBytes(), StandardOpenOption.APPEND);

                /*FileWriter writer = new FileWriter("file.txt", true);
                System.out.println(var);
                writer.write(var);
                writer.write("\n");
                writer.flush(); //????*/
                strings.remove(var);
                System.out.println("ThreadFile" + strings);
                if (strings.size() == 0) break;
            } catch (InterruptedException |IOException e) {
                    e.printStackTrace();
            }
        }
    }
}
