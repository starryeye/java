package thread.state.sub3_interrupt;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class Interrupt {

    /**
     * Thread 의 메서드를 다루다 보면, InterruptedException 캐치 예외를 자주 마주한다.
     *
     * InterruptedException ..
     * Thread 가 대기 상태(WAITING, TIMED_WAITING) 일 때, 해당 스레드에 thread.interrupt() 를 호출해주면
     * 대기 중 상태에서 InterruptedException 예외가 발생하며 RUNNABLE 상태로 변경된다.
     * 그래서, 개발자는 주로 Thread::sleep(), Thread::join() 등 스레드를 대기 상태로 만드는 메서드에서 던져지는 캐치 예외로 보게되며,
     *      해당 예외를 잡아서 처리하는 로직에는 인터럽트가 발생하여 RUNNABLE 상태가 되면 해야할 로직을 작성하면 된다.
     *
     * 참고.
     * interrupt 라는 상태가 따로 존재하는 것은 아니고 스레드 내부의 flag 에 가깝다.
     * 따라서, RUNNABLE 상태의 스레드에 interupt() 를 호출해도 아무런 반응이 없으며 내부 flag 만 변경된 꼴이 된다.
     *      대신, RUNNABLE 상태의 스레드에 interrupt() 를 호출한 상태에서 해당 스레드가 대기 상태로 변경되면
     *      바로 InterruptedException 이 발생하며 RUNNABLE 상태로 변경된다.
     *
     * 인터럽트 관련 메서드 정리
     * Thread::interrupt()
     *      해당 스레드에 interrupt flag 를 true 로 바꾼다.
     * Thread::isInterrupted()
     *      해당 스레드의 interrupt flag 를 반환해준다.
     * Thread::interrupted()
     *      해당 스레드의 interrupt flag 를 반환해준다. 만약 true 를 반환한다면 내부 flag 를 false 로 바꿔준다.
     *
     * Thread::sleep(), Thread::join() 메서드들과 같이, Java 에서 InterruptedException 이 던져지도록 해놓은 메서드를 이용하다가
     * InterruptedException 이 발생하면, 자동으로 interrupt flag 를 false 로 변경해준다.
     *
     *
     * 참고.
     * 개발자가 직접 interrupt flag 를 활용하는 코드를 작성한다면..
     * 아래와 같이 interrupt flag 를 다시 돌려놓는 Thread::interrupted() 메서드를 사용해야한다.
     */

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread taskThread = new Thread(task, "task-1");
        taskThread.start();

        mySleep(20);
        taskThread.interrupt(); // main 스레드가 task-1 스레드에 interrupt flag 를 true 로 만든다.
        threadLog(taskThread.getName() + " thread interrupt() called, thread isInterrupted() = " + taskThread.isInterrupted());
        // 바로 위 로그의 isInterrupted() 결과는 true 가 나오도록 의도한 것임. (OS 스케줄러에 의해 MyTask while 문 Thread.interrupted() 이 먼저 수행되면 false 로 나올 수도 있음.)
    }

    private static class MyTask implements Runnable {

        @Override
        public void run() {
            threadLog("start");

            while (!Thread.interrupted()) { // 만약 interrupted() 가 아닌 isInterrupted() 를 사용하면 resource cleaning failed 쪽으로 빠진다.
                threadLog("something task running..");
            }
            threadLog("this thread isInterrupted() = " + Thread.currentThread().isInterrupted()); // 항상 false

            try {
                threadLog("resource cleaning start");
                Thread.sleep(1000); // TIMED_WAITING
                threadLog("resource cleaning end");
            } catch (InterruptedException e) {
                // 이 코드 블럭에서는 interrupt flag 는 이미 false 이며, RUNNABLE 상태이다.
                threadLog("resource cleaning failed " + e.getMessage());
                threadLog("this thread isInterrupted() = " + Thread.currentThread().isInterrupted()); // 항상 false
            }

            threadLog("end");
        }
    }
}
