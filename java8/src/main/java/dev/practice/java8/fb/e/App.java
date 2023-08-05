package dev.practice.java8.fb.e;

public class App {

    /**
     * ExecutorService 의 execute 메서드와 submit 메서드는 모두 파라미터로 Runnable 을 받는다.
     * Runnable 은 함수형 인터페이스로 run 메서드의 리턴 타입은 void 이다.
     *
     * 따라서, 스레드 작업 수행 결과를 main 스레드가 받지 못한다.
     *
     * 그래서, Callable 을 사용하면 된다.
     * Callable 은 Runnable 에서 리턴 타입이 있는 것이라 생각하면 편하다.
     *
     * 
     */
}
