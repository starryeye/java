package dev.practice.sub5_proxy.nospring;

public class DefaultBookService implements BookService{
    @Override
    public void rent(Book book) {
        System.out.println("rent" + book.getTitle());
    }
}
