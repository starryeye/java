package concurrent.sub6_executor.sub2_using_and_flow;

import java.util.concurrent.*;

import static util.MyExecutorLog.executorLog;
import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class Flow2 {

    public static void main(String[] args) {

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ExecutorService es = new ThreadPoolExecutor(
                2, 4, 3000L, TimeUnit.MILLISECONDS, workQueue);
        executorLog(es, "init");



        es.execute(new RunnableTask("task 1", 1000));
        executorLog(es);
        es.execute(new RunnableTask("task 2", 1000));
        executorLog(es);



        es.execute(new RunnableTask("task 3", 1000));
        executorLog(es);
        es.execute(new RunnableTask("task 4", 1000));
        executorLog(es);



        es.execute(new RunnableTask("task 5", 1000));
        executorLog(es);
        es.execute(new RunnableTask("task 6", 1000));
        executorLog(es);



        try {
            es.execute(new RunnableTask("task 7", 1000));
        } catch (RejectedExecutionException e) {
            threadLog("executor service rejected task 7.. " + e);
        }



        mySleep(3000); // 모든 작업 종료 대기
        executorLog(es, "all task finished");




        mySleep(3000); // maximumPoolSize 만큼 늘어난 초과 스레드가 thread pool 에서 제거되는 시간 대기
        executorLog(es, "timeout (maximumPoolSize - corePoolSize) thread keepAliveTime");




        es.shutdown(); // non-blocking
        mySleep(1000); // shutdown 대기
        executorLog(es, "shutdown");
    }

    private static class RunnableTask implements Runnable {

        private final String taskName;
        private final int sleepTimeMillis;

        private RunnableTask(String taskName, int sleepTimeMillis) {
            this.taskName = taskName;
            this.sleepTimeMillis = sleepTimeMillis;
        }

        @Override
        public void run() {
            threadLog(taskName + " start");
            mySleep(sleepTimeMillis);
            threadLog(taskName + " end");
        }
    }
}
