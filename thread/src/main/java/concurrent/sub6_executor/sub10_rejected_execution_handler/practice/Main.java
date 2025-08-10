package concurrent.sub6_executor.sub10_rejected_execution_handler.practice;

import concurrent.sub6_executor.sub10_rejected_execution_handler.RejectedExecutionHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.MyExecutorLog.executorLog;
import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class Main {

    /**
     * 거절된 작업 갯수를 출력하는 RejectedExecutionHandler 를 적용시켜본다.
     */

    public static void main(String[] args) {

        ExecutorService es = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(), new CustomRejectedExecutionHandler()); // 커스텀 RejectedExecutionHandler 적용
        executorLog(es, "init");

        es.execute(new MyTask("task 1", 1000));
        executorLog(es, "task 1 executed..");

        es.execute(new MyTask("task 2", 1000));
        es.execute(new MyTask("task 3", 1000));
        es.execute(new MyTask("task 4", 1000));

        es.close(); // blocking
    }

    static class MyTask implements Runnable {

        private final String name;
        private final int sleepTimeMs;

        private MyTask(String name, int sleepTimeMs) {
            this.name = name;
            this.sleepTimeMs = sleepTimeMs;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            threadLog(name + " start");

            mySleep(sleepTimeMs);

            long end = System.currentTimeMillis();
            threadLog(name + " end, elapsed = " + (end - start));
        }

        public String getName() {
            return name;
        }
    }
}
