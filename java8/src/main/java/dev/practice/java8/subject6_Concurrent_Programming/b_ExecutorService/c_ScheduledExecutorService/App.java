package dev.practice.java8.subject6_Concurrent_Programming.b_ExecutorService.c_ScheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {

        /**
         * ScheduledExecutorService 사용
         */
        // ScheduledExecutorService 는 ExecutorService 를 상속 받는다.
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // 3초 후, 작업 시작
        scheduledExecutorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }
}
