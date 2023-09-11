package dev.practice.sub6_dynamicproxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    /**
     * JDK 동적 프록시 (다이나믹 프록시)
     *
     * sub5 에서는 컴파일 타임에 이미 프록시 객체가 존재한다.
     * 이번엔 런타임에 동적으로 프록시를 만들어보자.
     *
     * 즉, ProxyBookService 코드가 없는데 있는것 처럼 해보자는 것.
     */
    BookService bookService = (BookService) Proxy.newProxyInstance(
            BookService.class.getClassLoader(), //BookService 를 만든 클래스로더를 쓰면 당연히.. 프록시도 만들수 있겠지
            new Class[]{BookService.class}, // 생성할 프록시가 어떤 인터페이스 타입의 구현체인가..
            new InvocationHandler() { // 프록시의 어떤 메서드가 호출될때 무엇을 할 것인가.. Proxy 에서 할 동작 정의

                BookService bookService = new DefaultBookService(); // 타겟 객체
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    if(method.getName().equals("rent")) {
                        System.out.println("aaaaa"); // 부가 기능
                        Object invoke = method.invoke(bookService, args);// 타겟 객체의 메서드 호출
                        System.out.println("bbbbb"); // 부가 기능

                        return invoke;
                    }

                    return method.invoke(bookService, args);
                }
            }
    );

    /**
     * 문제점
     * - 프록시 클래스를 직접 작성하는 수고는 덜어졌지만...
     * - 프록시를 적용할 대상을 가려내는게 힘들어졌다. (메서드가 많아지면, 코드가 감당이 안될정도로..)
     * - 인터페이스만 가능하다. (JDK 동적 프록시 한정)
     *
     * 하지만 여기서 출발하여 스프링 AOP 가 나왔다.
     * 위의 문제점을 해결하였다.
     */

    @Test
    void proxy() {

        Book book = new Book();
        book.setTitle("Spring");

        bookService.rent(book); // 동적 프록시 객체를 통해 타겟 객체 호출, 프록시 내부 로직에 의해 부가기능 O
        bookService.buy(book); // 동적 프록시 객체를 통해 타겟 객체 호출, 프록시 내부 로직에 의해 부가기능 X
    }

}