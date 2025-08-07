package concurrent.sub6_executor.sub6_executor_service_invoke;

import java.util.List;
import java.util.concurrent.*;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class InvokeAll {

    /**
     * ExecutorService 에서 제공하는 invokeAll 메서드를 알아본다.
     *
     * invokeAll..
     *      <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException;
     *      파라미터로 전달한 Callable 을 모두 제출하고 제출한 모든 Callable 이 실행 완료될 때까지 대기한다.
     *      모든 작업이 완료된 후, List<Future<T>> 가 반환됨.
     *
     */

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(10);

        MyTask task1 = new MyTask("task 1", 1000);
        MyTask task2 = new MyTask("task 2", 2000);
        MyTask task3 = new MyTask("task 3", 3000);


        List<Future<String>> futures = es.invokeAll(List.of(task1, task2, task3)); // invokeAll 호출, 모든 작업이 완료된 후 반환된다.


        for (Future<String> future : futures) {
            String result = future.get();
            threadLog("result = " + result);
        }

        es.shutdown();
    }

    private static class MyTask implements Callable<String> {

        private final String name;
        private final int sleepTimeMs;

        private MyTask(String name, int sleepTimeMs) {
            this.name = name;
            this.sleepTimeMs = sleepTimeMs;
        }

        @Override
        public String call() throws Exception {
            long start = System.currentTimeMillis();
            threadLog(name + " start");

            mySleep(sleepTimeMs);

            long end = System.currentTimeMillis();
            threadLog(name + " end, elapsed = " + (end - start));
            return name;
        }
    }
}
