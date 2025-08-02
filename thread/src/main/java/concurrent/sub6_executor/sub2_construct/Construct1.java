package concurrent.sub6_executor.sub2_construct;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.MyExecutorLog.executorLog;
import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class Construct1 {

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(
                2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        executorLog(executorService, "init");

        executorService.execute(new MyTask("task 1", 1000));
        executorService.execute(new MyTask("task 2", 1000));
        executorService.execute(new MyTask("task 3", 1000));
        executorService.execute(new MyTask("task 4", 1000));
        executorLog(executorService, "tasks executed");

        mySleep(500);
        executorLog(executorService, "tasks running..");
        mySleep(500);
        executorLog(executorService, "tasks running..");

        mySleep(3000);
        executorLog(executorService, "tasks finished");

        executorService.shutdown();
        executorLog(executorService, "shutdown");
    }

    private static class MyTask implements Runnable {

        private final String taskName;
        private final int sleepTimeMills;

        public MyTask(String taskName, int sleepTimeMills) {
            this.taskName = taskName;
            this.sleepTimeMills = sleepTimeMills;
        }

        @Override
        public void run() {
            threadLog(taskName + " start");
            mySleep(sleepTimeMills);
            threadLog(taskName + " finish");
        }
    }
}
