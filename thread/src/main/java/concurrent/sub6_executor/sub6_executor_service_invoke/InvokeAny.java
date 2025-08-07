package concurrent.sub6_executor.sub6_executor_service_invoke;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class InvokeAny {

    /**
     * ExecutorService 에서 제공하는 invokeAny 메서드를 알아본다.
     *
     * invokeAny..
     *      <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;
     *      파라미터로 전달한 Callable 을 모두 제출하고 제출한 Callable 중 가장 먼저 완료된 작업을 반환한다.
     *          가장 먼저 완료되는 작업까지는 대기한다.
     *          첫 작업이 완료된 후, T 가 반환됨.
     *          나머지 작업들은 실행중인 스레드에서 인터럽트 발생
     */

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(10);

        MyTask task1 = new MyTask("task 1", 1000);
        MyTask task2 = new MyTask("task 2", 2000);
        MyTask task3 = new MyTask("task 3", 3000);

        String result = es.invokeAny(List.of(task1, task2, task3));// invokeAny 호출, 가장 먼저 완료된 작업의 반환값을 리턴, 그동안 대기

        threadLog("result = " + result);

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
