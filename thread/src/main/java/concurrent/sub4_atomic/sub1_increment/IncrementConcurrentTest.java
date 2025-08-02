package concurrent.sub4_atomic.sub1_increment;

import concurrent.sub4_atomic.sub1_increment.impl.AtomisticInteger;
import concurrent.sub4_atomic.sub1_increment.impl.BasicInteger;
import concurrent.sub4_atomic.sub1_increment.impl.SynchronizedInteger;
import concurrent.sub4_atomic.sub1_increment.impl.VolatileInteger;

import java.util.ArrayList;
import java.util.List;

import static util.MyThreadUtils.mySleep;

public class IncrementConcurrentTest {

    /**
     * 1000개의 스레드로 동시성 test 를 수행해본다.
     *
     * 연산
     * value++; 하나의 코드라인이지만..
     *      value = value + 1; 과 동일한 코드이며.. 아래와 같이 여러 연산으로 이루어져있다.
     *          1. value 를 메모리에서 읽고 +1 연산을 수행한 다음
     *          2. 결과값을 메모리에 업데이트한다.
     * todo, 해당 연산이 왜 CAS 를 적용할 수 있는 연산인지..
     *
     * 의의
     * BasicInteger
     *      동시성 문제 발생, 메모리 가시성 문제도 존재한다.
     * VolatileInteger
     *      메모리 가시성 문제를 해결하여도 여러개의 연산(조회 + 업데이트)으로 이루어져 있어서 동시성 문제 발생
     * SynchronizedInteger
     *      synchronized 키워드로 안전한 임계영역을 만들고 하나의 스레드만 공유자원에 접근하도록 하여 동시성 문제 해결
     *          Java 에서 동시성 문제를 해결하면 메모리 가시성 문제는 해결됨을 보장
     * AtomisticInteger (내부 AtomicInteger 사용)
     *      SynchronizedInteger 는 비관적 락 개념을 사용하지만, AtomicInteger 에서는 낙관적 락 개념을 사용하여 동시성 문제 해결
     *          자세한 내용은 AtomisticInteger 에 기술
     */

    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SynchronizedInteger());
        test(new AtomisticInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {

        Runnable runnable = () -> {
            mySleep(50); // 동시성 문제를 더욱 잘보이게 하기 위함..
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
        System.out.println(incrementInteger.getClass().getSimpleName() + ", result: " + result);
    }
}
