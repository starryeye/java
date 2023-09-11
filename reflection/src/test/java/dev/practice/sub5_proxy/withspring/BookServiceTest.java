package dev.practice.sub5_proxy.withspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    void proxy() {
        Book book = new Book();
        book.setTitle("Spring");
        bookService.rent(book);
    }

}