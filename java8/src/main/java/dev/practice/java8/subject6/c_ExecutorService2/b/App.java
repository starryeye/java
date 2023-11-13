package dev.practice.java8.subject6.c_ExecutorService2.b;

import java.util.List;
import java.util.concurrent.*;

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

        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 싱글 스레드.. 동시성..

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

        // invokeAll 을 사용해서 한꺼번에 보낼 수 있다.
        // invokeAll 은 hello, java, world 모두 끝날때 까지 기다린다......
        List<Future<String>> futures = executorService.invokeAll(List.of(hello, java, world)); //invokeAll 은 여기가 blocking 인거 같음..

        System.out.println("??"); //먼저 안찍힘..

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        executorService.shutdown();

    }
}
