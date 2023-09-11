package dev.practice.sub6_dynamicproxy;

public class DefaultBookService implements BookService{
    @Override
    public void rent(Book book) {
        System.out.println("rent : " + book.getTitle());
    }

    @Override
    public void buy(Book book) {
        System.out.println("buy : " + book.getTitle());
    }
}
