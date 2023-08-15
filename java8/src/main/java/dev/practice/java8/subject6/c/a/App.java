package dev.practice.java8.subject6.c.a;

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

        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 싱글 스레드

        //Callable<리턴타입>
        Callable<String> callable = () -> {
            Thread.sleep(5000L); //5초
            return "Hello";
        };

        // Callable 의 리턴 타입(String)의 Future 를 작업의 결과로 받을 수 있다. Future<String>
        // 사실 Runnable 때도 Future 를 받을 수 있었지만, 리턴이 null 이라서 의미 없음
        Future<String> future = executorService.submit(callable);
        System.out.println(future.isDone()); // 작업 종료 여부 확인
        System.out.println("started!!");

        // 다른 스레드에서 작업한 결과인 Callable 리턴 값을 main 스레드에서 받을 수 있다.
        future.get(); // 다른 스레드에서 결과를 줄 때 까지 Blocking, 블로킹 콜
//        future.cancel(true); // 작업 취소

        System.out.println(future.isDone()); // 작업 종료 여부 확인
        System.out.println("end!!");
        executorService.shutdown();

    }
}
