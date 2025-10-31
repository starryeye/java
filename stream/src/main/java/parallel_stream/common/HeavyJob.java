package parallel_stream.common;

import parallel_stream.util.MyThreadLog;

public abstract class HeavyJob {

    private HeavyJob() {
    }

    public static int heavyTask(int i) {

        try { // 무거운 작업이라고 가정한다.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int heavyTaskResult = i * 10;
        MyThreadLog.threadLog("completed heavy calculation.. " + i + " --> " + heavyTaskResult);
        return heavyTaskResult;
    }
}
