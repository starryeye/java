package concurrent.reentrant_lock;

public interface Lock {

    /**
     * Lock interface
     *
     * LockSupport 는 synchronized 의 단점을 해결하였지만 너무 저수준의 기능이라
     * Lock 인터페이스와 구현체 ReentrantLock 을 사용하면 고수준의 기능을 사용할 수 있다.
     *
     * void lock();
     * void lockInterruptibly() throws InterruptedException;
     * boolean tryLock();
     * boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
     * void unlock();
     * Condition newCondition();
     *
     */
}
