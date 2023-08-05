package dev.practice.java8.fb.b;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {

        /**
         * Thread Pool 사용
         */
        // 2개의 스레드(스레드 풀)를 가지고있는 ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 작업은 5개이지만, 스레드 2개로 결국 모두 수행한다.
        // 각 스레드에서 1개의 작업을 수행할 때, 나머지 3개의 작업은 Blocking Queue 에 쌓여있게 된다.
        executorService.submit(getRunnable("Hello"));
        executorService.submit(getRunnable("World"));
        executorService.submit(getRunnable("Java"));
        executorService.submit(getRunnable("Spring"));
        executorService.submit(getRunnable("Jpa"));

        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }
}
