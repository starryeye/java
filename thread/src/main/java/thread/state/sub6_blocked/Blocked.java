package thread.state.sub6_blocked;

import static util.MyThreadLog.threadLog;

public class Blocked {

    /**
     * BLOCKED 상태의 스레드는 락이 풀릴 때까지 무한 대기한다.
     *
     * 단점.
     * 1. 여러 스레드가 BLOCKED 상태로 대기한다면.. 어떤 스레드가 락을 획득할지 알 수 없다.
     *      최악의 경우 특정 스레드는 계속 대기할 수 있음..
     * 2. 무한 대기, 타임아웃 기능이나 인터럽트 기능도 없다.
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

        // BLOCKED 상태 출력을 위한 monitor
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
        private int count = 0;

        public synchronized void increment() {
            count++;
            /**
             * 코드가 한 줄 이지만, 동시성 문제가 발생한다.
             * count = count + 1; 로 풀어 쓸 수 있고
             * count 를 조회해서 업데이트하는 로직이고..
             * 여러 스레드가 동시에 조회하고 동시에 업데이트하면 씹힌다. (동시성 문제)
             * 따라서, 해당 메서드는 임계영역으로 synchronized 키워드를 사용해 해결함.
             */
        }

        public synchronized int getCount() {
            return count;
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
                if (state == State.BLOCKED) {
                    threadLog("target thread = " + target.getName() + ", state = " + state);
                    break;
                }
            }
        }
    }
}
