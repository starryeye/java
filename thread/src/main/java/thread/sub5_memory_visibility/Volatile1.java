package thread.sub5_memory_visibility;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class Volatile1 {

    /**
     * memory visibility(메모리 가시성) 문제..
     * 스레드 A, B가 동일한 메모리 값을 잘 보고 있다가..
     * 스레드 A에서 메모리 값을 변경했는데, 스레드 B에서 동일한 메모리 값을 읽었을 때 변경 되지 않은 것 처럼 보이는 현상..
     *
     * 메인 메모리(RAM)와 CPU 코어 사이에는 또다른 캐시 메모리(L1, L2 등)이 있어서
     * 스레드 A가 메모리 값을 변경하더라도 메인 메모리까지 반영이 늦거나
     * 또는 메인 메모리까지는 반영이 되더라도 스레드 B가 실행되고 있는 CPU 코어의 캐시 메모리까지 반영이 늦어서
     * 스레드 A가 변경한 값이 스레드 B가 읽을 때 즉시 보이지 않을 수 있다.
     *
     * 메모리 가시성 문제 발생 코드 특징..
     * 원자적 연산으로 이루어져있고(연산이 나누어져 있지 않아 동시성 문제 없음)
     * 여러 스레드가 읽고 쓰는 상황에 자주 보이는듯..
     *
     * 참고.
     * CPU 코어의 캐시메모리와 메인 메모리 사이의 싱크 문제라서 context switching 이 일어나면 보통 다시 싱크가 되기도 한다..
     *
     * 참고
     * 동시성 문제를 해결하기 위해 락을 사용하여 안전한 임계영역을 만들어주면 해당 케이스에서는 메모리 가시성 문제도 같이 해결된다. (happens before 관계)
     *
     * 해결..
     * volatile 키워드를 사용하면 해당 키워드가 사용된 메모리 값은 메인 메모리에서 직접 쓰고 읽도록 동작되어 해결된다. (성능은 안좋아짐..)
     */

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        threadLog("runFlag = " + task.runFlag);
        t.start();
        mySleep(1000);


        task.runFlag = false; // work 스레드가 종료되도록 flag 변경
        threadLog("tried runFlag false..  runFlag = " + task.runFlag);
        threadLog("end");
    }

    private static class MyTask implements Runnable {


        boolean runFlag = true; // volatile 키워드를 붙이지 않으면, work 스레드가 종료되지 않을 수 있다.
        //volatile boolean runFlag = true; // volatile 키워드를 붙이면, work 스레드가 확실하게 종료 된다.



        @Override
        public void run() {
            threadLog("task start");
            while (runFlag) {
                // runFlag 가 false 로 변하면 탈출
            }
            threadLog("task end");
        }
    }
}
