package concurrent.sub6_executor.sub9_thread_pool_strategy;

import java.util.concurrent.*;

import static util.MyExecutorLog.executorLog;
import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class CustomThreadPoolStrategy {

    /**
     * corePoolSize 보다 maximumPoolSize 를 크게 잡아서,
     *      갑자기 작업 요청이 많아질 경우를 대비한다.
     * corePoolSize 를 0 보다 크고 maximumPoolSize 보다 작게 잡아서,
     *      일반적인 상황에 CPU, 메모리 사용량을 예측가능하도록 고정적인 갯수로 잡는다.
     *
     * 참고.
     * TASK_SIZE_CASE_1 과 TASK_SIZE_CASE_2 를 실행해보면,
     *      하드웨어 사양이 어떤지 상관없이, 각각 대부분 11초, 6초 정도 걸린다.
     *      이는 MyTask 작업이 sleep 으로 thread 가 TIMED_WAITING 상태로 대기하므로 CPU 소모 없으므로
     *      CPU core 갯수나 처리 속도에 관련이 없어서 그런듯
     *
     */

    private static final int TASK_SIZE_CASE_1 = 1100; // corePoolSize thread 만 사용됨
    private static final int TASK_SIZE_CASE_2 = 1200; // maximumPoolSize thread 까지 사용됨
    private static final int TASK_SIZE_CASE_3 = 1201; // maximumPoolSize thread 까지 사용되고 마지막 1개는 거절됨


    public static void main(String[] args) {

        ExecutorService es = new ThreadPoolExecutor(100, 200,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
        executorLog(es, "init");


        int taskSize = TASK_SIZE_CASE_1; // case 별, test

        long start = System.currentTimeMillis();
        for (int i = 0; i < taskSize; i++) {
            try {
                es.execute(new MyTask("task " + i, 1000));
                executorLog(es, "task " + i + " executed");
            } catch (RejectedExecutionException e) {
                threadLog("task " + i + " rejected.. " + e);
            }
        }

        es.close(); // blocking
        long end = System.currentTimeMillis();
        threadLog("elapsed time: " + (end - start) + "ms");
    }

    private static class MyTask implements Runnable {

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
    }
}
