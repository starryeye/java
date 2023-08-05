package dev.practice.java8.fb.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    /**
     * Executors
     *
     * 고수준 (High-Level) Concurrency 프로그래밍
     * - 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리.
     * - 그런 기능을 Executors 에게 위임.
     *
     * Executors 가 하는 일
     * - 쓰레드 만들기 : 애플리케이션이 사용할 쓰레드 풀을 만들어 관리한다.
     * - 쓰레드 관리 : 쓰레드 생명 주기를 관리한다.
     * - 작업처리 및 실행 : 쓰레드로 실행할 작업을 제공할 수 있는 API 를 제공한다.
     *
     * 주요 인터페이스
     * - Executor: execute(Runnable)
     * - ExecutorService: Executor 상속 받은 인터페이스로, Callable 도 실행할 수 있으며, Executor 를 종료 시키거나, 여러 Callable 을 동시에 실행하는 등의 기능을 제공한다.
     * - ScheduledExecutorService: ExecutorService 를 상속 받은 인터페이스로 특정 시간 이후에 또는 주기적으로 작업을 실행할 수 있다.
     */


    /**
     * Executor
     * 쓰레드를 생성하고 관리하는 것을 고수준의 API 에게 위임하고 우리는 그것을 편하게 사용하기만 하면된다.
     *
     * Thread / Runnable 은 저수준 Concurrent 프로그래밍이지만...
     * Executor 는 고수준 Concurrent 프로그래밍을 지원한다.
     *
     */

    public static void main(String[] args) {

        //Executors 를 사용할 때는 ExecutorService 를 사용한다. (ExecutorService 는 Executor 를 상속받은 인터페이스이다.)
        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 스레드를 하나만 쓰는 executor 이다.

        // execute 메서드에 Runnable 구현을 넘겨줘서 실행( 혹은 submit 메서드를 사용해도 된다.)
        executorService.submit(() -> System.out.println("Thread : " + Thread.currentThread().getName()));

        // ExecutorService 는 명시적으로 shutdown 을 하지 않으면 죽지 않고 다음 작업이 들어올 때 까지 대기를 한다.
        // shutdown 기능은 graceful shutdown 이다.
        executorService.shutdown();

    }
}
