package concurrent.atomic.sub1_increment;

import concurrent.atomic.sub1_increment.impl.BasicInteger;
import concurrent.atomic.sub1_increment.impl.SynchronizedInteger;
import concurrent.atomic.sub1_increment.impl.VolatileInteger;

import java.util.ArrayList;
import java.util.List;

import static util.MyThreadUtils.mySleep;

public class IncrementConcurrentTest {

    /**
     * 1000개의 스레드로 동시성 test 를 수행해본다.
     *
     * 의의
     * BasicInteger
     *      동시성 문제 발생, 메모리 가시성 문제도 존재한다.
     * VolatileInteger
     *      메모리 가시성 문제를 해결하여도 여러개의 연산(조회 + 업데이트)으로 이루어져 있어서 동시성 문제 발생
     * SynchronizedInteger
     *      synchronized 키워드로 안전한 임계영역을 만들고 하나의 스레드만 공유자원에 접근하도록 하여 동시성 문제 해결
     */

    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SynchronizedInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {

        Runnable runnable = () -> {
            mySleep(10); // 동시성 문제를 더욱 잘보이게 하기 위함..
            incrementInteger.increment();
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + " result: " + result);
    }
}
