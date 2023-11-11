package dev.practice.java8.subject6.a.b_ThreadClass2;

public class App {

    /**
     * Java Concurrent Programming (동시성 프로그래밍)
     *
     * Java 에서 쓰레드를 만드는 두번째 방법
     * - Thread class 생성자에 Runnable 을 주입한다.
     */
    public static void main(String[] args) {

        // Runnable 인터페이스는 함수형 인터페이스이다.
        // 함수형 인터페이스는 람다 표현식으로 구현체를 만들 수 있다.
        Thread myThread = new Thread(() -> {
            System.out.println("Thread : " + Thread.currentThread().getName());
        });
        myThread.start();

        System.out.println("Hello : " + Thread.currentThread().getName());
        /**
         * 코드 상으로는 Thread 가 먼저 수행되었지만 내부적으로 두개의 쓰레드가 동작한다.
         * 두개의 스레드가 동작하여 출력 순서는 랜덤이다.
         */

    }
}
