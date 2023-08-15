package dev.practice.java8.subject6.e.b;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {

    /**
     * CompletableFuture API
     *
     * public <U,V> CompletableFuture<V> thenCombine(
     *         CompletionStage<? extends U> other,
     *         BiFunction<? super T,? super U,? extends V> fn)
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

        // thenCombine 으로 두개의 비동기 작업을 붙인다. (각각 따로 처리, 연관 없음)
        // 두개의 작업 결과로 새로운 결과를 만들어 낸다. (BiFunction)
        CompletableFuture<String> helloWorld = hello.thenCombine(world, (s1, s2) -> {
            System.out.println("Hello World " + Thread.currentThread().getName());
            return s1 + " " + s2;
        });

        System.out.println(helloWorld.get());
    }
}
