package concurrent.sub2_lock.sub1_reentrant_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyThreadLog.threadLog;

public class ReentrantLock1 {

    /**
     * ReentrantLock 설명과 예시1
     *
     * Lock interface 의 대표적 구현체이다.
     * Lock interface 가 제공하는 기능을 충실히 구현함과 동시에 락 대기의 공정성 문제 (fair / non-fair mode 를 제공함)
     *
     * Non-fair mode
     *      new ReentrantLock(); 로 생성하면 된다.
     *      락 획득 속도가 빠르지만, 운이 좋지 않은 스레드는 오랜시간 락을 획득 못할 수 있음 (대부분은 선착순이긴함)
     * fair mode
     *      new ReentrantLock(true); 로 생성하면 된다.
     *      락 획득 속도가 느릴 수 있음, 락 대기 큐에서 선착순으로 락을 획득 할 수 있다.
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

        // WAITING 상태 출력을 위한 monitor
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

            lock.lock(); // 락을 획득하면 아래 코드 계속 진행(RUNNABLE), 락 획득 하지 못하면 WAITING 으로 대기
            try {

                count++;
                /**
                 * 코드가 한 줄 이지만, 동시성 문제가 발생한다.
                 * count = count + 1; 로 풀어 쓸 수 있고
                 * count 를 조회해서 업데이트하는 로직이고..
                 * 여러 스레드가 동시에 조회하고 동시에 업데이트하면 씹힌다. (동시성 문제)
                 * 따라서, 해당 코드 한줄은 ReentrantLock 을 사용하여 보호해준다.
                 */

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
                if (state == State.BLOCKED || state == State.WAITING) {
                    threadLog("target thread = " + target.getName() + ", state = " + state);
                    break;
                }
            }
        }
    }
}
