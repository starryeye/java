package dev.practice.java8.fe.a;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {

    /**
     * CompletableFuture API
     *
     * public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 두개의 비동기 작업을 이어 붙이기가 용이하다.
         *
         * 이전에는 hello.get() 으로 결과를 받아서 처리..
         * 지금은 메서드 체인으로 가능 (fluent)
         */

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        // thenCompose API 로 두개의 비동기 작업을 이어붙일 수 있다.
        CompletableFuture<String> helloWorld = hello.thenCompose(App::getWorld);// hello 결과로 getWorld 에 넘겨주고 해당 인풋을 바탕으로 두번째 비동기 작업 처리

        System.out.println(helloWorld.get()); // 두개의 비동기 작업을 순차적으로 처리하고 결과를 받아 출력


    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
