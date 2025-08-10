package concurrent.sub6_executor.sub10_rejected_execution_handler.practice;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyThreadLog.threadLog;

public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        if (r instanceof Main.MyTask task) {
            int rejected = count.incrementAndGet();
            threadLog("[WARNING!] rejected task name: " + task.getName() + ", total rejected: " + rejected);
        }

        // MyTask 타입이 아니면, DiscardPolicy 를 따르도록 의도함
    }
}
