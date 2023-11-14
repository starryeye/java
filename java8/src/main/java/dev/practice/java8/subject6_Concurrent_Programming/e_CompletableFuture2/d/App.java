package dev.practice.java8.subject6_Concurrent_Programming.e_CompletableFuture2.d;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {

    /**
     * CompletableFuture API
     *
     * public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 두개의 비동기 작업을 따로 따로 처리하고 (서로 연관 없음)
         * 두개의 결과가 도착하면 해당 결과를 출력
         *
         * 이전에는 hello.get() 으로 결과를 받아서 처리..
         * 지금은 메서드 체인으로 가능 (fluent)
         */

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });


        // anyOf 메서드를 사용하여..
        // 두개의 비동기 작업을 각각 수행하여
        // 가장 빠르게 종료되는 작업으로 한번의 콜백인 thenAccept 를 수행한다.
        CompletableFuture<Void> result = CompletableFuture.anyOf(hello, world)
                .thenAccept((s) -> {
                    System.out.println(s + " " + Thread.currentThread().getName());
                });

        result.get(); // 로그 출력을 위해 대기

        /**
         * 여기서도 allOf 때 처럼
         * 콜백이 작업 스레드에서 처리하거나..
         * 이상하게 main 스레드에서 처리할 때가 있는듯하다.
         */
    }
}
