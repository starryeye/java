package util;

import static util.MyThreadLog.threadLog;

public class MyThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            threadLog("interrupt exception occur, " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
