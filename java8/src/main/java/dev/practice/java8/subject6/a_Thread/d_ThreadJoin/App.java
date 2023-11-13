package dev.practice.java8.subject6.a_Thread.d_ThreadJoin;

public class App {

    /**
     * Java Concurrent Programming (동시성 프로그래밍)
     */
    public static void main(String[] args) throws InterruptedException {

        Thread myThread = new Thread(() -> {

            System.out.println("Thread : " + Thread.currentThread().getName());

            try {
                Thread.sleep(3000L); // 3초간 현재 스레드 sleep, 다른 스레드에게 자원을 할당한다. 다른 스레드가 먼저 일을 한다.
            } catch (InterruptedException e) {
                System.out.println("interrupted, thread : " + Thread.currentThread().getName());
                throw new IllegalStateException(e);
            }
        });
        myThread.start();

        System.out.println("Hello : " + Thread.currentThread().getName());
        /**
         * 두개의 출력 순서는 거의 확정적으로 Hello 가 Thread 보다 먼저이다.
         */

        try {
            myThread.join(); // main 스레드가 myThread 가 종료될 때까지 기다린다. (대기)
        }catch (InterruptedException e) {
            // main thread 가 myThread 를 기다리는 동안 InterruptedException 이 발생될 수 있다.
            // 대기한다는 것은 sleep 처럼 다른 스레드에게 CPU 자원이 넘어가는 것을 의미한다.
            throw new IllegalStateException(e);
        }

        System.out.println(myThread + " is finished");


        //프로그래머가 수십 수백개의 스레드를 위와 같이 직접 관리한다는 것은 힘들고 사실상 하면 안된다.
        // -> Executor 을 사용하자.

    }
}
