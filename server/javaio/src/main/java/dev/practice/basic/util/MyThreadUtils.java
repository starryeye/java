package dev.practice.basic.util;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class MyThreadUtils {

    /**
     * mySleep() 을 사용하는 코드에서 try-catch 를 가려서 가독성을 좋게하는 용도
     */
    public static void mySleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            threadLog("interrupt exception occur, " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
