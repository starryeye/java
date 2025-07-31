package concurrent.atomic.sub3_spin_lock;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class SpinLockMain {

    public static void main(String[] args) {

        MySpinLock mySpinLock = new MySpinLock();

        Runnable task = () -> {
            mySpinLock.lock();
            try {

                // 임계 영역 시작
                threadLog("this is critical section..");
                mySleep(1); // 비즈니스 로직 수행한다고 가정
                // 임계 영역 끝

            } finally {
                mySpinLock.unlock();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
