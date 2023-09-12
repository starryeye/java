package dev.practice.sub7_dynamicproxycglib;

public class DefaultBookService {
    public void rent(Book book) {
        System.out.println("rent : " + book.getTitle());
    }

    public void buy(Book book) {
        System.out.println("buy : " + book.getTitle());
    }
}
