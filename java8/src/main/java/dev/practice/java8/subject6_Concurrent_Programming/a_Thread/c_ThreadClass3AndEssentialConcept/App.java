package dev.practice.java8.subject6_Concurrent_Programming.a_Thread.c_ThreadClass3AndEssentialConcept;

public class App {

    /**
     * Java Concurrent Programming (동시성 프로그래밍)
     */

    /**
     * 참고
     *
     * 스레드는 프로그램에서 병행적으로 처리될 수 있는 실행의 최소 단위로 간주한다.
     * 동시성(concurrency)와 병렬성(parallelism)은 다른 개념이다.
     *
     * 동시성(Concurrency)
     * 여러 작업이 동시에 발생하는 것처럼 보이는 것을 의미한다.
     * 이는 실제로 동시에 실행되는 것이 아니라, 여러 작업이 시간을 공유하며 번갈아 가면서 실행되는 것을 의미한다.
     * 이런 동시성은 단일 CPU 코어에서도 이루어질 수 있다.
     *
     * 병렬성(Parallelism)은 여러 작업이 실제로 동시에 실행되는 것을 의미한다.
     * 이는 여러 CPU 코어나 컴퓨터가 있는 경우에만 가능하다.
     *
     * 따라서, 스레드는 동시성을 지원하며, 동시성은 CPU 코어의 수와는 무관하다.
     * 하지만 스레드의 병렬성은 CPU 코어의 수에 의해 제한된다.
     * CPU 코어의 수가 많을수록 더 많은 스레드를 실제로 동시에 실행된다.
     * 즉, 쓰레드를 이용하여 작업을 비동기적으로 처리하는 것은..
     * CPU core 수에 따라 동시성이 될 수도.. 병렬성이 될 수도 있는 것이다.
     *
     * CPU 코어의 수는 프로세스와 스레드 모두에게 병렬 처리를 위한 주요한 자원이다.
     * 다중 코어 CPU에서 각 코어는 독립적으로 작업을 수행할 수 있으므로, 여러 프로세스나 스레드를 병렬로 실행할 수 있다.
     *
     * 프로세스와 스레드는 약간 다른 방식으로 병렬 처리된다
     * 프로세스
     * - 각 프로세스는 독립된 메모리 공간을 가지므로, 한 프로세스에서 발생하는 오류는 다른 프로세스에 영향을 미치지 않는다.
     * - 이는 안정성을 높이지만, 프로세스 간 통신은 상대적으로 비용이 많이 든다.
     *
     * 스레드
     * - 같은 프로세스 내의 스레드들은 메모리를 공유하므로, 스레드 간 통신은 비교적 간단하고 빠르다.
     * - 그러나, 이는 한 스레드에서 발생하는 오류가 다른 스레드에 영향을 미칠 수 있다는 단점도 있다.
     */
    public static void main(String[] args) throws InterruptedException {

        Thread myThread = new Thread(() -> {

            while (true) { // 무한 반복
                try {
                    Thread.sleep(1000L); // 1초간 현재 스레드 sleep, 다른 스레드에게 자원을 할당한다. 다른 스레드가 먼저 일을 한다.
                } catch (InterruptedException e) {
                    // 누군가 자는 스레드를 깨우면 여기가 실행된다.
                    System.out.println("exit, thread : " + Thread.currentThread().getName());
                    return; // Runnable run() 메서드의 리턴타입은 void 이다. 스레드 종료! (while 루프 벗어남)
                }
                System.out.println("Thread : " + Thread.currentThread().getName());
            }
        });
        myThread.start();

        System.out.println("Hello : " + Thread.currentThread().getName());
        /**
         * 두개의 출력 순서는 거의 확정적으로 Hello 가 Thread 보다 먼저이다.
         */

        Thread.sleep(3000L);
        myThread.interrupt(); // myThread interrupt 발생, 참고로 다른 쓰레드를 종료시키는 메서드는 없다!

    }
}
