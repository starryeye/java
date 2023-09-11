package dev.practice.sub5_proxy.nospring;

public class ProxyBookService implements BookService{

    private final BookService bookService;

    public ProxyBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("aaaaa");
        bookService.rent(book);
        System.out.println("bbbbb");
    }
}
