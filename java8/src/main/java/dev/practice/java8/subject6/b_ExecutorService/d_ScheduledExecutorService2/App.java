package dev.practice.java8.subject6.b_ExecutorService.d_ScheduledExecutorService2;

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

        // 1초 후 작업 시작, 2초 간격으로 작업 반복
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);

        //shutdown 이 있으면 작업 시작이 안되는 듯....?
//        scheduledExecutorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }
}
