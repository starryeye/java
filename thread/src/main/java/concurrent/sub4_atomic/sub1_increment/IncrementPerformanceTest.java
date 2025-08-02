package concurrent.sub4_atomic.sub1_increment;

import concurrent.sub4_atomic.sub1_increment.impl.AtomisticInteger;
import concurrent.sub4_atomic.sub1_increment.impl.BasicInteger;
import concurrent.sub4_atomic.sub1_increment.impl.SynchronizedInteger;
import concurrent.sub4_atomic.sub1_increment.impl.VolatileInteger;

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
     * AtomisticInteger
     *      락 프리 기법으로 수행하여, 앞 뒤에서 락을 획득하고 반납하는 연산이 없어서 상당히 빨라졌다. (VolatileInteger 보다 빠름..)
     *          이 테스트에서는 하나의 스레드로 스레드 충돌 상황이 없긴함..
     *
     * 참고.
     * AtomisticInteger 에서는 락을 획득하고 반납하는 과정이 없긴하지만, CAS 연산을 하면서 스레드 충돌하는 상황이 일어나면..
     * 재시도를 하기 때문에 좀 더 느려지는 것아닌가.. 라는 생각이 들 수 있지만..
     * CPU 의 연산 속도는 굉장히 빠르고
     */

    private static final long COUNT = 100_000_000L;

    public static void main(String[] args) {
        test(new BasicInteger());
        test(new VolatileInteger());
        test(new SynchronizedInteger());
        test(new AtomisticInteger());
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
