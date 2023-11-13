package dev.practice.java8.subject6.c_ExecutorService2.c;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 스레드 2개 이하로 할 경우.. 리스트 순서로 hello, java 가 어쨋든 먼저 수행되기 때문에 world 가 안나오고 hello 가 먼저 종료 될 수 있다.

        Callable<String> hello = () -> {
            Thread.sleep(5000L); //5초
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(10000L); //10초
            return "Java";
        };

        Callable<String> world = () -> {
            Thread.sleep(1000L); //1초
            return "World";
        };

        // invokeAny 을 사용해서 한꺼번에 보낼 수 있다.
        // invokeAny 은 hello, java, world 중 가장 먼저 끝난 결과를 리턴시킨다.
        // invokeAny 이 리턴은 Future 가 아니라 Callable 의 리턴 타입이다.
        String ret = executorService.invokeAny(List.of(hello, java, world)); //invokeAny 은 여기가 blocking

        System.out.println(ret);



        executorService.shutdown();

    }
}
