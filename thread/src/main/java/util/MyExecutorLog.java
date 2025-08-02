package util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyThreadLog.threadLog;

public abstract class MyExecutorLog {

    private MyExecutorLog() {}

    public static void executorLog(ExecutorService executorService) {
        logExecutorStatus(executorService, null);
    }

    public static void executorLog(ExecutorService executorService, Object message) {
        logExecutorStatus(executorService, message);
    }

    private static void logExecutorStatus(ExecutorService executorService, Object message) {

        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            StringBuilder sb = new StringBuilder();
            sb.append("[")
                    .append("thread pool state : ")
                    .append("size=").append(poolExecutor.getPoolSize())                             // 스레드 풀에서 관리되는 스레드 갯수
                    .append(", activeCount=").append(poolExecutor.getActiveCount())                 // 스레드 풀에서 현재 RUNNABLE 상태의 스레드 갯수
                    .append(", queuedTaskCount=").append(poolExecutor.getQueue().size())            // 스레드 풀에 제출한 작업들 중, 실제 수행되기 까지 대기 중인 작업의 갯수
                    .append(", completedTaskCount=").append(poolExecutor.getCompletedTaskCount())   // 스레드 풀에 제출한 작업들 중, 실제 수행 완료된 작업의 갯수
                    .append("]");

            if (message != null) {
                sb.append(" ").append(message);
            }

            threadLog(sb.toString());
        } else {
            threadLog(executorService);
        }
    }
}
