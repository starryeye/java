package dev.practice.sub7_dynamicproxycglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBookServiceTest {

    /**
     * JDK 동적 프록시 방법은 인터페이스가 있어야 그 인터페이스를 바탕으로 프록시 객체를 만들수 있었다.
     * - 인터페이스는 프록시 대상 타겟 객체의 인터페이스이다.
     * - 프록시에서 타겟 객체를 호출 한다.
     *
     * 인터페이스가 없는 경우에는 어떻게 프록시 객체를 만들 수 있는지..
     * -> CGlib 라이브러리를 사용한다.
     *
     *
     */

    @Test
    void proxy() {

        MethodInterceptor handler = new MethodInterceptor() {

            DefaultBookService bookService = new DefaultBookService();

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

                if(method.getName().equals("rent")) { // 특정 메서드에만 부가기능(프록시) 적용
                    System.out.println("aaaaaa"); // 부가 기능
                    Object invoke = proxy.invoke(bookService, args);// 타겟 객체와 파라미터를 넘겨준다.
                    System.out.println("aaaaaa"); // 부가 기능
                    return invoke;
                }
                return proxy.invoke(bookService, args);
            }
        };


        /**
         * 프록시 객체 생성
         * Enhancer.create()
         * - BookService.class 는 대상 객체 "Class" 타입의 객체
         * - handler 는 프록시 객체가 할 일
         *
         * 참고
         * https://github.com/cglib/cglib/issues/191
         * 아래를 VM Option 에 추가해야한다.
         * --add-opens java.base/java.lang=ALL-UNNAMED
         * -> 이걸로 해결안되서 그냥 .. Spring framework 에 있는 CGlib 라이브러리를 사용했다...
         * -> 원래는 build.gradle 에 cglib 를 추가해서 직접 사용하려고 시도했었음..
         */
        DefaultBookService bookService = (DefaultBookService) Enhancer.create(DefaultBookService.class, handler);

        Book book = new Book();
        book.setTitle("Spring");

        bookService.rent(book);
        bookService.buy(book);
    }

}