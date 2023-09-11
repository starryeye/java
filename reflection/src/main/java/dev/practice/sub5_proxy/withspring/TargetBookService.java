package dev.practice.sub5_proxy.withspring;

import org.springframework.stereotype.Service;

@Service
public class TargetBookService implements BookService{
    @Override
    public void rent(Book book) {
        System.out.println("rent : " + book.getTitle());
    }
}
