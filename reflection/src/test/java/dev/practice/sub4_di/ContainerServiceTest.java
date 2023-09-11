package dev.practice.sub4_di;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerServiceTest {

    /**
     * ContainerService 에 BookRepository 의 "Class" 타입의 객체를 넘겨주면
     * ContainerService 에서 BookRepository 인스턴스를 생성해서 리턴해준다. ("Class" Type 의 객체를 이용한 리플랙션)
     */
    @Test
    public void getObject_BookRepository() {

        BookRepository bookRepository = ContainerService.getObject(BookRepository.class);

        assertNotNull(bookRepository);
    }

    /**
     * ContainerService 에 BookService 의 "Class" 타입의 객체를 넘겨주면
     * ContainerService 에서 BookService 인스턴스를 생성해서 리턴해준다.
     *
     * 내부적으로 BookService 의 "Class" 타입의 객체로 필드를 조회해서
     * 해당 필드에 @Inject 어노테이션이 존재하면,
     * 해당 필드의 인스턴스(BookRepository)도 생성하여 주입한 후, BookService 인스턴스를 리턴해준다.
     */
    @Test
    public void getObject_BookService() {

        BookService bookService = ContainerService.getObject(BookService.class);

        assertNotNull(bookService);
        assertNotNull(bookService.bookRepository);
    }
}