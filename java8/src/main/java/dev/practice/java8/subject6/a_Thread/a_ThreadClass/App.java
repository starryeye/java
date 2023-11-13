package dev.practice.java8.subject6.a_Thread.a_ThreadClass;

public class App {

    /**
     * Java Concurrent Programming (동시성 프로그래밍)
     *
     * Concurrent Software
     * - 동시에 여러 작업을 할 수 있는 소프트웨어
     * - 예) 웹 브라우저로 유튜브를 보면서 키보드로 문서에 타이핑할 수있다.
     * - 예) 녹화를 하면서 IntelliJ 로 코딩을 하고 워드에 적어둔 문서를 보거나 수정할 수 있다.
     *
     * Java 에서 지원하는 Concurrent Programming
     * - Multi Processing (ProcessBuilder 을 사용)
     * - Multi Thread
     *
     * Java Multi Thread Programming
     * - Thread / Runnable
     *
     * Java 에서 쓰레드를 만드는 첫번째 방법 (불편..)
     * - Thread class 를 상속 받아서 사용
     */
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("Hello : " + Thread.currentThread().getName());
        /**
         * 코드 상으로는 Thread 가 먼저 수행되었지만 내부적으로 두개의 쓰레드가 동작한다.
         * 두개의 스레드가 동작하여 출력 순서는 랜덤이다.
         */

    }

    // main() 이 static 이라 static
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread : " + Thread.currentThread().getName());
        }
    }
}
