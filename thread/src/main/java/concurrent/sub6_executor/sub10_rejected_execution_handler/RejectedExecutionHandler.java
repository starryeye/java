package concurrent.sub6_executor.sub10_rejected_execution_handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.MyExecutorLog.executorLog;
import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class RejectedExecutionHandler {

    /**
     * thread pool 이 처리하지 못할 정도로 작업 요청되어 blocking queue 에 작업이 가득 찰 경우
     * 어떻게 할 지에 대한 정책을 정할 수 있다.
     *
     * ThreadPoolExecutor 에는 다음과 같은 정책을 제공한다.
     *      아래 예시와 같이 ThreadPoolExecutor 생성자 마지막 파라미터로 전달하여 설정가능
     *
     * 정책
     * ThreadPoolExecutor.AbortPolicy()
     *      기본 값
     *      RejectExecutionException 을 발생시킨다.
     * ThreadPoolExecutor.DiscardPolicy()
     *      요청을 무시한다. (아무런 일도 일어나지 않음)
     * ThreadPoolExecutor.CallerRunsPolicy()
     *      작업을 요청한 스레드(생산자)가 직접 작업을 수행한다. (thread pool 은 아무것도 안함)
     *      생산자 스레드가 소비자일을 대신해주기 때문에, 생산 자체가 느려질 수 가 있다. (요청 처리량 하락)
     * 또한, RejectedExecutionHandler 를 개발자가 직접 구현하여 전달해줄 수 도 있다.
     */

    public static void main(String[] args) {

        // corePoolSize, maximumPoolSize 가 1로 동일하여 fixed pool 전략이며,
        // SynchronousQueue 이라서 blocking queue 의 사이즈가 0 으로 생산자가 소비자에게 직접 전달하는 메커니즘을 따른다.
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy()); // ThreadPoolExecutor.DiscardPolicy() 적용
        executorLog(es, "init");

        es.execute(new MyTask("task 1", 1000));
        executorLog(es, "task 1 executed..");

        es.execute(new MyTask("task 2", 1000)); // DiscardPolicy 이므로 초과된 작업은 무시된다.

        es.close(); // blocking
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
