package concurrent.sub8_completable_future.sub2_run;

public interface RunCompletableFuture {

    /**
     * 기존의 Future 는 ExecutorService::submit(Callable) 의 반환 값으로..
     *      ExecutorService 가 Callable 을 실행하고 반환 값으로 Future 을 이용한다는 개념이다.
     * 즉, Future 자체로는 실행할 수 있는 개념이 아니다.
     *
     * 하지만, CompletableFuture 는 그 자체로 실행하는 api 를 제공한다.
     *      ExecutorService 의 api 로는 CompleatableFuture 를 반환 받을 수 없다.
     *
     *
     * CompletableFuture 의 실행 api
     * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
     * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
     * static CompletableFuture<Void> runAsync(Runnable runnable)
     * static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
     * static <U> CompletableFuture<U> completedFuture(U value)
     *
     * TODO
     */
}
