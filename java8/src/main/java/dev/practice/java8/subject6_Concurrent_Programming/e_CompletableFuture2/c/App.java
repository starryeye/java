package dev.practice.java8.subject6_Concurrent_Programming.e_CompletableFuture2.c;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    /**
     * CompletableFuture API
     *
     * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
     *
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * 두개의 비동기 작업을 따로 따로 처리하고 (서로 연관 없음)
         * 두개의 결과가 도착하면 해당 결과를 출력
         *
         * 이전에는 hello.get() 으로 결과를 받아서 처리..
         * 지금은 메서드 체인으로 가능 (fluent)
         */

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService);

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        }, executorService);

        List<CompletableFuture<String>> futures = List.of(hello, world); // 수행하고자 하는 비동기 작업의 CompletableFuture 모음
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]); // Array 로 변환

        // allOf 메서드..
        // 매개변수로 전달한 CompletableFuture Array 를 비동기로 각각 수행한다.
        // thenApply 로..
        // allOf 결과 (null 이라 의미없음) 는 의미 없이 받기만 하고
        // future List 를 stream 으로 CompletableFuture 결과를 List 로 받아낸다.
        // 결론적으로 allOf 메서드의 결과가 void 라는 것을 회피하여 결과를 받아낸 것이다.
        CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
                .thenApply((t) -> {
                    System.out.println("thenApply " + Thread.currentThread().getName());
                    return futures.stream()
                        .map(CompletableFuture::join)
                        .toList();
                });

        /**
         * TODO
         * 위에서 get 대신 join 을 사용한 이유는 get 은 checked exception 을 처리해야하지만..
         * join 은 unchecked exception 으로 처리할 수 있기 때문이다.
         *
         * allOf 는 전달된 CompletableFuture Array 를 처리한다.
         * thenApply 로 모든 작업결과에 대해 콜백을 수행 할 것이라 생각.. 하였지만..
         * 콜백이 아니라 main 으로 처리 된 것도 이상하고.. 각각 수행되어 총 2회 가 아니라 딱 한번만 수행된 것도 이상하다..
         *
         *
         * 참고>
         * main 으로 출력된 것은.. 랜덤이다.
         * 작업 스레드가 콜백을 수행할때도 있고 이상하게 main 스레드가 콜백을 수행할 때도 있네...
         */

        results.get().forEach(System.out::println);
    }
}
