package thread.sub1_construct;

import static util.MyThreadLog.threadLog;

public class Construct2 {

    /**
     * Runnable 함수형 인터페이스를 구현하고 이용하여 Thread 를 생성하고 실행할 수 있다.
     */

    public static void main(String[] args) {
        threadLog("is start");

        Runnable runnable = () -> threadLog("is running");
        Thread thread = new Thread(runnable);
        thread.start();

        threadLog("is end");
    }
}
