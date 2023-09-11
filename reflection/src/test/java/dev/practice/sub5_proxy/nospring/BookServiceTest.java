package dev.practice.sub5_proxy.nospring;

import dev.practice.sub5_proxy.nospring.Book;
import dev.practice.sub5_proxy.nospring.BookService;
import dev.practice.sub5_proxy.nospring.DefaultBookService;
import dev.practice.sub5_proxy.nospring.ProxyBookService;
import org.junit.jupiter.api.Test;

class BookServiceTest {

    BookService bookService = new ProxyBookService(new DefaultBookService());

    @Test
    void proxy() {

        Book book = new Book();
        book.setTitle("Spring");
        bookService.rent(book);
    }

}