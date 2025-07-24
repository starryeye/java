package concurrent.lock_support;

import java.util.concurrent.locks.LockSupport;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class LockSupport1 {

    /**
     * synchronized 는 아래와 같은 단점이 존재한다.
     *      1. BLOCKED 상태를 이용하기 때문에..
     *          락이 풀릴 때 까지 무한 대기하여, 특정 시간까지만 대기할 수 없다.(타임 아웃 기능 X)
     *          BLOCKED 는 인터럽트가 안먹힌다.
     *      2. 여러 스레드가 BLOCKED 상태라면 어떤 스레드가 락을 획득할지 보장이 안되어 무한 대기할 가능성이 존재한다.
     *
     *
     * LockSupport 는 java.util.concurrent (Java 1.5 버전) 라는 동시성 문제 해결을 위한 라이브러리에서 제공한다.
     * 위의 synchronized 의 단점을 해결해준다.
     *
     * 락 획득에 실패한 스레드는
     * 스레드를 BLOCKED 상태로 차단 시키지 않고 WAITING 상태로 대기 시킨다.
     *      그래서, interrupt 먹힘
     *
     * park()
     *      스레드를 WAITING 상태로 변경한다.
     * parkNano(nanos)
     *      스레드를 TIMED_WAITING 상태로 변경하고 정한 시간이 지나면 RUNNABLE 상태로 변경된다.
     * unpark(thread)
     *      파라미터로 전달한 WAITING 상태의 스레드를 RUNNABLE 상태로 변경한다.
     *
     */

    public static void main(String[] args) throws InterruptedException {

        threadLog("====== park / unpark ======");
        Thread parkThread1 = new Thread(new ParkTask(), "park-1");
        parkThread1.start();

        mySleep(100); // parkThread 상태 출력을 위함
        threadLog("parkThread1 state = " + parkThread1.getState()); // WAITING

        threadLog("call LockSupport.unpark(parkThread1)");
        LockSupport.unpark(parkThread1);
        parkThread1.join();



        threadLog("====== park / interrupt ======");
        Thread parkThread2 = new Thread(new ParkTask(), "park-2");
        parkThread2.start();

        mySleep(100); // parkThread 상태 출력을 위함
        threadLog("parkThread2 state = " + parkThread2.getState()); // WAITING

        threadLog("call parkThread2.interrupt()");
        parkThread2.interrupt();
        parkThread2.join();




        threadLog("====== parkNanos ======");
        Thread parkNanosThread = new Thread(new ParkNanosTask(), "parkNanos-1");
        parkNanosThread.start();

        mySleep(100); // parkNanosThread 상태 출력을 위함
        threadLog("parkNanos-1 state = " + parkNanosThread.getState()); // TIMED_WAITING
    }

    private static class ParkTask implements Runnable {

        @Override
        public void run() {
            threadLog("call LockSupport.park()");

            LockSupport.park(); // 여기서 현재 스레드는 WAITING 상태로 대기한다.

            threadLog("LockSupport.park() finished, state = " + Thread.currentThread().getState() + ", isInterrupted = " + Thread.currentThread().isInterrupted() + "\n");
        }
    }

    private static class ParkNanosTask implements Runnable {

        @Override
        public void run() {
            threadLog("call LockSupport.parkNanos(nanos)");

            LockSupport.parkNanos(2000_000000); // 여기서 현재 스레드는 2초간 WAITING 상태로 대기한다.

            threadLog("LockSupport.parkNanos(nanos) finished, state = " + Thread.currentThread().getState() + ", isInterrupted = " + Thread.currentThread().isInterrupted() + "\n");
        }
    }
}
