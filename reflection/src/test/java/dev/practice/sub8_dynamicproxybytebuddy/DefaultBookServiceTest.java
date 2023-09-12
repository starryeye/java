package dev.practice.sub8_dynamicproxybytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.*;

class DefaultBookServiceTest {

    /**
     * JDK 동적 프록시 방법은 인터페이스가 있어야 그 인터페이스를 바탕으로 프록시 객체를 만들수 있었다.
     * - 인터페이스는 프록시 대상 타겟 객체의 인터페이스이다.
     * - 프록시에서 타겟 객체를 호출 한다.
     *
     * 인터페이스가 없는 경우에는 어떻게 프록시 객체를 만들 수 있는지..
     * -> 여기선 byte buddy 를 사용해보겠다.
     */

    @Test
    void proxy() throws Exception{
        /**
         * byte buddy 는 프록시 인스턴스를 만들어주진 않는다.
         * "Class" 타입의 객체를 만들어준다.
         * 해당 "Class" 타입의 객체로 프록시 인스턴스를 만든다.
         */
        Class<? extends DefaultBookService> proxyClass = new ByteBuddy()
                .subclass(DefaultBookService.class) //프록시 객체 대상 타겟 클래스
                .method(named("rent")) // 부가 기능 적용할 메서드, matcher
                .intercept(InvocationHandlerAdapter.of(new InvocationHandler() { // 프록시가 할 일

                    DefaultBookService bookService = new DefaultBookService(); // 타겟 객체
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("aaaaaa"); // 부가 기능
                        Object invoke = method.invoke(bookService, args); // 타겟 객체 호출
                        System.out.println("bbbbbb"); // 부가 기능

                        return invoke;
                    }
                }))
                .make()
                .load(DefaultBookService.class.getClassLoader())
                .getLoaded();

        DefaultBookService bookService = proxyClass.getConstructor().newInstance(); //프록시 인스턴스 생성, default 생성자를 이용

        Book book = new Book();
        book.setTitle("Spring");

        bookService.rent(book);
        bookService.buy(book);
    }

}