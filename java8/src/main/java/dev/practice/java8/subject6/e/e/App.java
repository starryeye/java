package dev.practice.java8.subject6.e.e;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {

    /**
     * CompletableFuture API
     * <p>
     * 비동기 작업 내부의 예외 처리
     *
     * public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn)
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        boolean throwError = true;

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {

            System.out.println("Hello " + Thread.currentThread().getName());

            if (throwError) {
                throw new IllegalArgumentException();
            }

            return "Hello";
        }).exceptionally(ex -> { // 콜백으로 처리된다.
            System.out.println(ex + " " + Thread.currentThread().getName());
            return "World";
        });

        // exceptionally 말고 handle 을 사용하면 좀더 제네럴한 방법이다.
        // result 에는 비동기 작업의 정상 결과, ex 에는 예외가 들어오게된다.
//        .handle((result, ex) -> {
//            if(ex != null) {
//                System.out.println(ex + " " + Thread.currentThread().getName());
//                return "World";
//            }
//            return result;
//        });

        System.out.println(hello.get());
    }
}
