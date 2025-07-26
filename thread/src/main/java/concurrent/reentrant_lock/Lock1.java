package concurrent.reentrant_lock;

public interface Lock1 {

    /**
     * Lock interface
     * 임계영역을 위한 락을 구현하는데 사용되는 인터페이스이다.
     * synchronized 키워드의 단점을 해결한다.
     *      참고. Lock 에서 사용되는 락은 synchronized 에서 사용되는 모니터 락이 아니다.
     *
     * LockSupport 는 synchronized 의 단점을 해결하였지만 너무 저수준의 기능이라
     * Lock 인터페이스와 구현체 ReentrantLock 을 사용하면 고수준의 기능을 사용할 수 있다.
     *
     * void lock();
     *      락 획득 시도, 다른 스레드가 이미 락을 획득한 상태라면 현재 스레드는 WAITING 상태가 된다.
     *      다른 스레드에서의 interrupt 가 안먹힌다.
     * void lockInterruptibly() throws InterruptedException;
     *      락 획득 시도, 다른 스레드가 이미 락을 획득한 상태라면 현재 스레드는 WAITING 상태가 된다.
     *      다른 스레드에서의 interrupt 가 먹힌다. (WAITING 스레드는 InterruptedException 발생, RUNNABLE 상태)
     * boolean tryLock();
     *      락 획득 시도, 다른 스레드가 이미 락을 획득한 상태라면 false 반환된다. (락을 획득하면 true 반환)
     * boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
     *      락 획득 시도, 다른 스레드가 이미 락을 획득한 상태라면 현재 스레드는 락을 획득할 때 까지 TIMED_WAITING 상태가 된다.
     *          파라미터의 시간이 지났고 다른 스레드가 이미 락을 획득한 상태라면 false 반환된다. (그 전에 락을 획득하면 true 반환)
     *      다른 스레드에서의 interrupt 가 먹힌다. (WAITING 스레드는 InterruptedException 발생, RUNNABLE 상태)
     * void unlock();
     *      현재 스레드가 가진 락을 해제한다. 락 획득을 대기(WAITING) 중인 스레드들 중 하나가 락을 획득한다.
     *          만약 락을 가지지 않은 스레드가 호출하면, IllegalMonitorStateException 발생한다.
     * Condition newCondition();
     *      Condition 객체를 생성하여 반환한다.
     *
     * 참고.
     * lock() 에서 WAITING 상태임에도 다른 스레드에서 interrupt 시도해도 안먹히는 이유는
     * WAITING -> RUNNABLE -> WAITING 으로 아주 짧게 빠져나오고 다시 WAITING 으로 강제 전환 시킨다.
     * -> synchronized 와 동일한 기능을 제공하기 위한 메서드라 생각하자
     *
     */
}
