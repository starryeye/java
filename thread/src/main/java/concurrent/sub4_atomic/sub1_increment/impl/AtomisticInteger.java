package concurrent.sub4_atomic.sub1_increment.impl;

import concurrent.sub4_atomic.sub1_increment.IncrementInteger;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomisticInteger implements IncrementInteger {

    /**
     * IncrementInteger 를 상속하도록하여 테스트 코드에서 다양한 구현체들간 일관된 api 로 사용되게끔함.
     * -> 그래서 AtomicInteger wrapping 함
     *
     * CAS (Compare And Swap, Compare And Set) 연산, 락 프리 기법
     *      락을 걸지 않고 원자적인 연산을 수행할 수 있는 방법이다.
     *      하지만, 락을 완전히 대체하지는 못하고 락을 사용해야할 특별한 케이스에 한해서 적용가능하다.
     *
     * 원리..
     *      AtomicXXX 의 클래스에서 제공하는 메서드들 중 compareAndSet(expectedValue, expectedValue) 메서드는 원자적으로 실행된다.
     *      생각해보면.. expectedValue 이라면, expectedValue 로 바꾼다는 연산인데..
     *          이 연산도 현재 메모리의 값이 무엇인지 확인하는 "조회"와 어떤 값으로 "업데이트" 하는 지와 같이 두개의 연산으로 이루어져있다..
     *          해당 연산을 원자적으로 컴퓨터 CPU 하드웨어에서 지원해준다... (두개의 연산을 실행하는데 다른 CPU 코어(스레드)가 개입 불가능하다.)
     */

    AtomicInteger atomicInteger = new AtomicInteger(0); // 초기값 0

    @Override
    public void increment() {
        atomicInteger.incrementAndGet();
//        myIncrementAndGet();
    }

    @Override
    public int get() {
        return atomicInteger.get();
    }

    private int myIncrementAndGet() {
        /**
         * AtomicInteger 는 해당 메서드를 incrementAndGet 으로 제공한다. (이해를 위해 구현했지만, 최적화 면에서 차이가 존재..)
         *
         * 설명
         * 일단 compareAndSet 연산은 두개처럼 보이지만, CPU 하드웨어 지원으로 원자적 연산이다.
         * 그리고 get() 과 compareAndSet() 이 두개의 연산이라 다른 스레드와 동시성 문제가 발생할 수 있는데.. (원자적 연산이 아님)
         *      이 경우에는 do-while 문으로 compareAndSet 이 실패한 경우 무한 재시도로 보완한다.
         *      -> 낙관적 락 기법
         */
        int getValue;
        boolean result;
        do {
            getValue = atomicInteger.get(); // 조회
            result = atomicInteger.compareAndSet(getValue, getValue + 1); // CAS
//            threadLog("thread conflict");
        } while (!result);

        return getValue + 1;
    }
}
