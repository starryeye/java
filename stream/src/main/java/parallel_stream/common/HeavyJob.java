package parallel_stream.common;

import parallel_stream.util.MyThreadLog;

public abstract class HeavyJob {

    /**
     * HeavyJob.heavyTask() 는 무거운 CPU 작업이라 가정한다.
     *
     * 하지만, 코드만 보면 Thread.sleep() 으로 thread 가 TIMED_WAITING 상태가 되는 것으로..
     * TIMED_WAITING 상태 동안 해당 thread 는 blocking 되어 아무것도 못하며, 해당 core 는 유휴 상태가 된다.
     * -> I/O bound 작업에 가깝다.
     */

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
