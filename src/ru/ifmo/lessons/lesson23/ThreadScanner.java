package ru.ifmo.lessons.lesson23;

import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;


public class ThreadScanner extends  Thread{

    CopyOnWriteArrayList<String> strings;

    public ThreadScanner (CopyOnWriteArrayList<String> strings) {
        this.strings = strings;
    }

    public void run() {

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Введите строку \n");
            strings.add(in.nextLine());
            System.out.println("ThreadScanner" + strings);
            if (strings.size() == 10) break;
        }
    }
}
