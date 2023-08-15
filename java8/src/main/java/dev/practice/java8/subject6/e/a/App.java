package dev.practice.java8.subject6.e.a;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {

    /**
     * 조합하기
     * - thenCompose(): 두 작업이 서로 이어서 실행하도록 조합
     * - thenCombine(): 두 작업을 독립적으로 실행하고 둘 다 종료 했을 때 콜백 실행
     * - allOf(): 여러 작업을 모두 실행하고 모든 작업 결과에 콜백 실행
     * - anyOf(): 여러 작업 중에 가장 빨리 끝난 하나의 결과에 콜백 실행
     *
     * 예외처리
     * - exeptionally(Function)
     * - handle(BiFunction)
     *
     * 참고
     * - https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html
     * - https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html
     */

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
