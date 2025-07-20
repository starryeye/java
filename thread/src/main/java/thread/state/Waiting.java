package thread.state;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.sleep;

public class Waiting {

    /**
     * Thread 의 WAITING 상태는 다른 스레드의 작업이 종료될 때까지 무기한 대기하고 있는 상태를 말한다.
     *
     * WAITING 상태의 스레드는 CPU 코어를 점유하지 않는다. (RUNNABLE 상태만 아니면 CPU 코어 점유 X)
     *
     * 참고.
     * Thread::join 을 사용하지 않고,
     * while (thread1.getState() != TERMINATED) {} 로 기다린다면..
     *      이렇게 하면 main 스레드는 WAITING 상태로 대기하지 않고 RUNNABLE 상태로 대기한다.(논리적으로 대기, 실제로는 state 확인을 하는 연산중)
     *      CPU 코어를 점유하게되고 자원의 낭비이다.
     *
     * 참고.
     * Thread::join(long millis) 를 호출하면 WAITING 이 아니라 TIMED_WAITING 상태이다.
     */

    public static void main(String[] args) throws InterruptedException {
        threadLog("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");
        thread1.start();
        thread2.start();

        // main 스레드 상태값 모니터링 (WAITING 출력용)
        Thread mainThread = Thread.currentThread();
        MonitoringTask monitoringTask = new MonitoringTask(mainThread, "monitor");
        monitoringTask.start();

        threadLog("Thread::join() called");
        thread1.join(); // thread1 의 작업이 완료될 때까지 무기한 대기, WAITING
        thread2.join(); // thread2 의 작업이 완료될 때까지 무기한 대기, WAITING
        threadLog("Thread::join() end");


        threadLog("task1.result = " + task1.result);
        threadLog("task2.result = " + task2.result);

        int sumAll = task1.result + task2.result;
        threadLog("task1 + task2 = "+ sumAll);
        threadLog("End");
    }

    private static class SumTask implements Runnable {

        private final int startValue;
        private final int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            threadLog("task start");

            sleep(2000); // 작업이 오래걸린다고 가정 (TIMED_WAITING 이지만 RUNNABLE 로 생각하자)

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;

            threadLog("task end result = " + result);
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
                if (state == Thread.State.WAITING) {
                    threadLog("target thread = " + target.getName() + ", state = " + state);
                    break;
                }
            }
        }
    }
}
