package concurrent.sub4_atomic.sub3_spin_lock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyThreadLog.threadLog;

public class MySpinLock {

    /**
     * CAS 방식으로 Lock 을 구현함.
     *
     * AtomicBoolean 을 사용하여.. 아래 두 연산을 원자적 연산으로 만들었다.
     *      1. 락 사용 여부 확인(locked 값 false 인지..)
     *      2. 락 획득(locked 값 true 로 변경)
     *
     * 참고.
     * spin lock 을 위해 Java 에서 제공하는 객체가 없다.
     * 아래와 같이 AtomicBoolean 을 이용해서 직접 구현해야한다.
     * 스핀 락은 락프리(CAS) 방식으로 구현되기 때문에 CAS 의 장단점을 가진다.
     *      가장 큰 특징은, 동기화 락 방식과 다르게 락 획득을 위해 대기하는 스레드가 busy waiting 으로 대기한다. (CPU 소모, 대기 중 RUNNABLE 상태 유지)
     */

    private final AtomicBoolean locked = new AtomicBoolean(false);

    public void lock() {
        while (!locked.compareAndSet(false, true)) {
            // 락 획득 실패 시 스핀 대기, busy waiting(thread RUNNABLE 상태), cpu 소모
            threadLog("failed to acquire lock.. busy waiting(thread - RUNNABLE)");
        }
        threadLog("lock acquired");
    }

    public void unlock() {
        locked.set(false); // 락 반납, 해당 연산은 원자적 연산으로 동시성 문제가 없다. (메모리 가시성 문제가 있지만, AtomicXXX 을 사용하여 해결되는듯..)
    }
}
