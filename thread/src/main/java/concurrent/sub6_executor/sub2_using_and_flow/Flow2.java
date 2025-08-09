package concurrent.sub6_executor.sub2_using_and_flow;

import java.util.concurrent.*;

import static util.MyExecutorLog.executorLog;
import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class Flow2 {

    /**
     * Executor framework 에서 thread pool 을 어떻게 관리하는지 상세하게 다루어본다.
     */

    public static void main(String[] args) {

        // ---------------------단계 구분----------------------------
        // 생성 단계
        // ExecutorService 가 생성되면 기본적으로 thread pool 에 thread 는 만들어지지 않는다.
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ExecutorService es = new ThreadPoolExecutor(
                2, 4, 3000L, TimeUnit.MILLISECONDS, workQueue);
        executorLog(es, "init"); // size=0, activeCount=0, queuedTaskCount=0, completedTaskCount=0



        // ---------------------단계 구분----------------------------
        // 1. main 스레드가 executor 에 작업(task 1)을 요청
        //      executor 는 thread pool 에 thread 가 corePoolSize 만큼 존재하는지 확인 -> corePoolSize 만큼 없음
        //      thread 하나를 생성
        //      생성된 스레드는 작업을 바로 실행한다. (작업은 blocking queue 에 들어가지 않음)
        // 2. main 스레드가 executor 에 작업(task 2)을 요청
        //      1번 과정과 동일
        es.execute(new RunnableTask("task 1", 1000));
        executorLog(es); // size=1, activeCount=1, queuedTaskCount=0, completedTaskCount=0
        es.execute(new RunnableTask("task 2", 1000));
        executorLog(es); // size=2, activeCount=2, queuedTaskCount=0, completedTaskCount=0



        // ---------------------단계 구분----------------------------
        // 1. main 스레드가 executor 에 작업(task 3)을 요청
        //      executor 는 thread pool 에 thread 가 corePoolSize 만큼 존재하는지 확인 -> corePoolSize 만큼 존재
        //      executor 는 thread pool 에 놀고 있는 thread 가 있는지 확인 -> 없음
        //      executor 는 blocking queue 에 자리가 있는지 확인 -> 자리가 있음
        //      blocking queue 에 작업을 보관
        // 2. main 스레드가 executor 에 작업(task 4)을 요청
        //      1번 과정과 동일
        es.execute(new RunnableTask("task 3", 1000));
        executorLog(es); // size=2, activeCount=2, queuedTaskCount=1, completedTaskCount=0
        es.execute(new RunnableTask("task 4", 1000));
        executorLog(es); // size=2, activeCount=2, queuedTaskCount=2, completedTaskCount=0



        // ---------------------단계 구분----------------------------
        // 1. main 스레드가 executor 에 작업(task 5)을 요청
        //      executor 는 thread pool 에 thread 가 corePoolSize 만큼 존재하는지 확인 -> corePoolSize 만큼 존재
        //      executor 는 thread pool 에 놀고 있는 thread 가 있는지 확인 -> 없음
        //      executor 는 blocking queue 에 자리가 있는지 확인 -> 자리가 없음
        //      executor 는 maximumPoolSize 까지 "초과 스레드"를 생성 (요청이 들어오면 한개씩 생성, (maximumPoolSize - corePoolSize) 갯수 만큼 "초과 스레드"))
        //      생성된 스레드는 작업(task 5)을 바로 실행한다. (작업은 blocking queue 에 들어가지 않음)
        // 2. main 스레드가 executor 에 작업(task 6)을 요청
        //      1번 과정과 동일
        es.execute(new RunnableTask("task 5", 1000));
        executorLog(es); // size=3, activeCount=3, queuedTaskCount=2, completedTaskCount=0
        es.execute(new RunnableTask("task 6", 1000));
        executorLog(es); // size=4, activeCount=4, queuedTaskCount=2, completedTaskCount=0



        // ---------------------단계 구분----------------------------
        // 1. main 스레드가 executor 에 작업(task 7)을 요청
        //      executor 는 thread pool 에 thread 가 corePoolSize 만큼 존재하는지 확인 -> corePoolSize 만큼 존재
        //      executor 는 thread pool 에 놀고 있는 thread 가 있는지 확인 -> 없음
        //      executor 는 blocking queue 에 자리가 있는지 확인 -> 자리가 없음
        //      executor 는 maximumPoolSize 까지 "초과 스레드"를 생성되었는지 확인 -> 초과 스레드까지 이미 생성됨
        //      main 스레드 RejectedExecutionException 예외 발생, 작업 거절
        try {
            es.execute(new RunnableTask("task 7", 1000));
        } catch (RejectedExecutionException e) {
            threadLog("executor service rejected task 7.. " + e);
        }



        // ---------------------단계 구분----------------------------
        mySleep(3000); // 모든 작업 종료까지 대기
        executorLog(es, "all task finished"); // size=4, activeCount=0, queuedTaskCount=0, completedTaskCount=6




        // ---------------------단계 구분----------------------------
        mySleep(3000); // maximumPoolSize 만큼 늘어난 초과 스레드가 thread pool 에서 제거되는 시간 대기
        executorLog(es, "timeout (maximumPoolSize - corePoolSize) thread keepAliveTime"); // size=2, activeCount=0, queuedTaskCount=0, completedTaskCount=6




        // ---------------------단계 구분----------------------------
        es.shutdown(); // non-blocking
        mySleep(1000); // shutdown 대기
        executorLog(es, "shutdown"); // size=0, activeCount=0, queuedTaskCount=0, completedTaskCount=6
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
