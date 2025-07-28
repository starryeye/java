package concurrent.atomic.sub1_test;

import concurrent.atomic.sub1_test.impl.BasicInteger;
import concurrent.atomic.sub1_test.impl.SynchronizedInteger;
import concurrent.atomic.sub1_test.impl.VolatileInteger;

public class IncrementPerformanceTest {

    /**
     * 하나의 스레드로 performance test 를 수행해본다.
     *
     * 의의
     * BasicInteger
     *      CPU 캐시 메모리의 위력..
     * VolatileInteger
     *      메인 메모리를 직접 사용하여 CPU 캐시 메모리 보다 느림
     * SynchronizedInteger
     *      임계영역 앞뒤로 락을 획득하고 반납하는 연산이 추가되어 가장 느림
     */

    private static final long COUNT = 100_000_000L;

    public static void main(String[] args) {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SynchronizedInteger());
    }

    private static void test(IncrementInteger incrementInteger) {
        long startTime = System.currentTimeMillis();

        for (long i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long endTime = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + ": elapsed time = " + (endTime - startTime));
    }
}
