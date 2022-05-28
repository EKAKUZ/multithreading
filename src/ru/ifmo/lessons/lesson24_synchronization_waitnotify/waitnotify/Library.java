package ru.ifmo.lessons.lesson24_synchronization_waitnotify.waitnotify;

import java.util.ArrayList;
//  у класса  object и наследников есть методы относящиеся к многопоточности,
//  но они не стабильные и используются редко
// wait -  приостанавливает работу потока,
// пока не будет разбужен вызовом метода notify из другого потока
// могут востановить работу сами по себе


// notify(); - возобновляет работу случайного потока,
// который был приостановлен вызовом метода wait()
// нельзя указать какой именно поток должен востановить работу

// notifyAll(); - возобнавляет работу всех потоков,
// которые были приостановлены вызовом метода wait()

//можно вызвать только в sincronaized методе или блоке


public class Library {
    private ArrayList<Book> books = new ArrayList<>(6);

    public synchronized void putBook(Book book) throws InterruptedException { // не более 6 книг
        //if (books.size() > 5) wait();
        while (books.size() > 5) { // while используется так как wait -
            // может неожиданно прекратить работу, и поток возобновится,
            // поэтому после окончания ожидания еще раз проверяем условие
            wait();
        }
        books.add(book);
        System.out.println("Книга добавлена, всего книг: " + books.size());
        notifyAll();
    }

    public synchronized Book getBook() throws InterruptedException { //нельзя получить книгу из пустой коллекции
        while (books.size() == 0) {
            wait();
        }
        Book book = books.remove(0);
        System.out.println("Удалена книга, всего книг: " + books.size());
        notifyAll();
        return book;
    }

    static class Book{}
}