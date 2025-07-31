package concurrent.atomic.sub3_spin_lock;

import java.util.concurrent.atomic.AtomicBoolean;

import static util.MyThreadLog.threadLog;

public class MySpinLock {

    /**
     * CAS 방식으로 Lock 을 구현함.
     *
     * AtomicBoolean 을 사용하여.. 아래 두 연산을 원자적 연산으로 만들었다.
     *      1. 락 사용 여부 확인(locked 값 false 인지..)
     *      2. 락 획득(locked 값 true 로 변경)
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
