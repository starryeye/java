package concurrent.reentrant_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyThreadLog.threadLog;

public class ReentrantLock2 {

    /**
     * boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
     *      락 획득 시도, 다른 스레드가 이미 락을 획득한 상태라면 현재 스레드는 TIMED_WAITING 상태가 된다.
     *          파라미터의 시간이 지났고 다른 스레드가 이미 락을 획득한 상태라면 false 반환된다. (그 전에 락을 획득하면 true 반환)
     *      다른 스레드에서의 interrupt 가 먹힌다. (TIMED_WAITING 스레드는 InterruptedException 발생, RUNNABLE 상태)
     *
     * 위 메서드를 이용해본다.
     *
     * 참고.
     * 가끔 BLOCKED 상태가 로그로 보인다.
     *      ReentrantLock 에서는 WAITING이 일반적이고, BLOCKED가 나왔다면 이는 lock queue 처리 중 일시적인 상태로 가끔 잡히는 것..
     */

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        // TIMED_WAITING 상태 출력을 위한 monitor
        Thread monitor = new MonitoringTask(thread3, "monitor");
        monitor.start();


        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        threadLog("result = " + counter.getCount());
    }

    private static class Counter {

        private final Lock lock = new ReentrantLock();

        private int count = 0;

        public void increment() {

            try {
                if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) { // 락을 획득하면 임계영역으로 진입 (RUNNABLE), 락 획득 하지 못하면 TIMED_WAITING 으로 대기
                    // 0.5 초 동안 락 획득을 하지 못하면..
                    threadLog("tryLock failed.. thread = " + Thread.currentThread().getName());
                    return;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                // 임계 영역
                count++;

            } finally {
                lock.unlock();
            }
        }

        public int getCount() {

            lock.lock();
            try {

                return count;

            } finally {
                lock.unlock();
            }
        }
    }

    private static class MonitoringTask extends Thread {

        private final Thread target;

        public MonitoringTask(Thread target, String name) {
            super(name);
            this.target = target;
        }

        @Override
        public void run() {
            while (true) {
                Thread.State state = target.getState();
                if (state == State.BLOCKED || state == State.WAITING || state == State.TIMED_WAITING) {
                    threadLog("target thread = " + target.getName() + ", state = " + state);
                    break;
                }
            }
        }
    }
}
