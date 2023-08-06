package dev.practice.java8.fd;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    /**
     * CompletableFuture..
     * - 자바에서 비동기(Asynchronous) 프로그래밍을 가능케 하는 인터페이스.
     * - Future 를 사용해서도 어느정도 가능했지만 하기 힘든 일들이 많았다.
     *
     * Future로는 하기 어렵던 작업들
     * - Future를 외부에서 완료 시킬 수 없다. 취소하거나, get()에 타임아웃을 설정할 수는 있다.
     * - 블로킹 코드(get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다.
     * - 여러 Future 를 조합할 수 없다. 예) Event 정보 가져온 다음 Event 에 참석하는 회원 목록 가져오기
     * - 예외 처리용 API를 제공하지 않는다.
     *
     * CompletableFuture
     * - Implements Future
     * - Implements CompletionStage
     *
     * 비동기로 작업 실행하기
     * - 리턴값이 없는 경우: runAsync()
     * - 리턴값이 있는 경우: supplyAsync()
     * - 원하는 Executor(쓰레드풀)를 사용해서 실행할 수도 있다. (기본은 ForkJoinPool.commonPool())
     *
     * 콜백 제공하기
     * - thenApply(Function): 리턴값을 받아서 다른 값으로 바꾸는 콜백
     * - thenAccept(Consumer): 리턴값을 또 다른 작업을 처리하는 콜백 (리턴없이)
     * - thenRun(Runnable): 리턴값 받지 다른 작업을 처리하는 콜백
     * - 콜백 자체를 또 다른 쓰레드에서 실행할 수 있다.
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        /**
         * 아래 코드를  "// --" 를 분기로 주석 처리 후 돌려야한다.
         * 주석처리하지 않고 돌리면 원래 알던 것과 좀 다르게 동작하는 것 같다..
         *
         * TODO
         * - callback 코드가 비동기 스레드에서 수행되지 않고 main 에서 수행된다거나..
         *
         *
         */


        // CompletableFuture 를 사용하면 외부에서 명시적으로 작업을 끝낼 수 있다.
        CompletableFuture<String> hello1 = CompletableFuture.completedFuture("Hello1");
        System.out.println(hello1.get());


        // --


        // CompletableFuture 를 사용하면 외부에서 명시적으로 작업을 끝낼 수 있다.
        CompletableFuture<String> hello2 = new CompletableFuture<>();
        hello2.complete("Hello2");
        System.out.println(hello2.get());


        // --


        // 비동기 실행, 리턴이 없는 경우 runAsync(Runnable)
        CompletableFuture<Void> hello3 = CompletableFuture.runAsync(() -> {
            System.out.println("Hello3 " + Thread.currentThread().getName());
        });
        hello3.get();


        // --


        // 비동기 실행, 리턴이 있는 경우 supplyAsync(Supplier)
        // TODO, Callable vs Supplier
        CompletableFuture<String> hello4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello4 " + Thread.currentThread().getName());
            return "Hello4";
        });
        System.out.println(hello4.get());


        // --


        // 비동기 실행, + 콜백 (이전 Future 에서 CompletableFuture 로 넘어오면서 달라진점)
        // thenApply(Function)
        CompletableFuture<String> hello5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello5 " + Thread.currentThread().getName());
            return "Hello5";
        }).thenApply((s) -> {
            System.out.println("callback5 " + Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(hello5.get());


        // --


        // 비동기 실행, + 콜백 (이전 Future 에서 CompletableFuture 로 넘어오면서 달라진점)
        // thenAccept(Consumer)
        CompletableFuture<Void> hello6 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello6 " + Thread.currentThread().getName());
            return "Hello6";
        }).thenAccept((s) -> {
            System.out.println("callback6 " + Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });
        hello6.get();


        // --


        // 비동기 실행, + 콜백 (이전 Future 에서 CompletableFuture 로 넘어오면서 달라진점)
        // thenRun(Runnable)
        CompletableFuture<Void> hello7 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello7 " + Thread.currentThread().getName());
            return "Hello7";
        }).thenRun(() -> {
            System.out.println("callback7 " + Thread.currentThread().getName());
        });
        hello7.get();


        // --


        /**
         * runAsync, supplyAsync 파라미터 두번째 인자로 Executor 를 넘겨서 개발자가 생성한 스레드 풀에서
         * 스레드를 할당하여 작업을 하도록 만들 수 있다.
         * 위 예제 코드와 같이 두번째 인자를 사용하지 않으면, ForkJoinPool 을 사용한다.
         *
         * TODO, ForkJoinPool 알아보기
         *
         * thenRun, thenAccept, thenApply 등의 메서드에 thenRunAsync 와 같이 Async 를 붙인 메서드들이 존재하는데
         * 두번째 인자로 Executor 를 넣어 줄 수 있다. 개발자가 지정한 스레드 풀로 콜백을 수행하도록 할 수 있는 것이다.
         *
         * Executor 를 사용할 땐... shutdown 을 해줘야한다..안그러면 스레드 종료가 되지 않음....
         */
    }
}
