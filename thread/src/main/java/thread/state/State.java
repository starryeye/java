package thread.state;

import static util.MyThreadLog.threadLog;

public class State {

    /**
     * Thread.State
     *      NEW             // [    생성] start() 호출 전
     *      RUNNABLE        // [실행 가능] CPU 에 의해 실행되고 있거나, OS 스케줄러 실행 대기열에 있거나
     *      BLOCKED         // [    차단] 동기화 락을 기다리는 상태                                     ex. synchronized 블록 진입 대기
     *      WAITING         // [    대기] 무기한으로 다른 스레드의 특정 작업이 완료되기를 기다리는 상태          ex. ..
     *      TIMED_WAITING   // [시간 대기] 일정 시간동안 기다리는 상태                                    ex. sleep()
     *      TERMINATED      // [    종료] 실행을 마친 상태
     *
     *     생성  --> 실행 가능
     * 실행 가능 <--> 일시 중지(차단, 대기, 시간 대기)
     * 실행 가능  --> 종료
     */

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread("my-thread");
        myThread.start();

        Thread.sleep(1000); // sleep 중인 스레드의 상태를 출력하기 위함
        threadLog("my-thread state: " + myThread.getState()); // TIMED_WAITING

        Thread.sleep(3000); // 종료된 스레드의 상태를 출력하기 위함
        threadLog("my-thread state: " + myThread.getState()); // TERMINATED
    }

    private static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);

            threadLog("MyThread::MyThread is called");
            threadLog("my-thread state: " + getState()); // NEW
        }

        @Override
        public void run() {
            threadLog("MyThread::run is called");
            threadLog("my-thread state: " + getState()); // RUNNABLE

            doSleep();

            threadLog("my-thread state: " + getState()); // RUNNABLE
            threadLog("MyThread::run is terminated");
        }

        private void doSleep() {
            try {
                threadLog("MyThread::doSleep is called");
                Thread.sleep(3000);
                threadLog("MyThread::doSleep is terminated");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
