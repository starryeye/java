package dev.practice.sub5_proxy.withspring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ProxyBookService implements BookService{

    private final BookService bookService;

    public ProxyBookService(@Qualifier("targetBookService") BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("aaaaaaaa");
        bookService.rent(book);
        System.out.println("bbbbbbbb");
    }
}
