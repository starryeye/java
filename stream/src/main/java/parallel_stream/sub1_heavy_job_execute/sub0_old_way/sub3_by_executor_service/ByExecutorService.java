package parallel_stream.sub1_heavy_job_execute.sub0_old_way.sub3_by_executor_service;

import parallel_stream.common.HeavyJob;
import parallel_stream.util.MyThreadLog;

import java.util.concurrent.*;

public class ByExecutorService {

    /**
     * 무거운 여러 개의 작업을..
     * thread pool 을 사용하여 멀티 스레드(2개의 스레드)로 처리해본다.
     *
     * 결과
     *      약 4 초가 걸림.
     *
     * 의의
     *      ByMultiThread 와 비교하여
     *      직접 thread 를 다루지 않고 thread pool 을 사용하여 편리하게 thread 를 관리할 수 있다.
     *      Callable, Future 를 사용하여 편리하게 작업의 최종 결과를 얻을 수 있다.
     *
     *
     * 참고
     * 여기서의 무거운 작업이란..
     * parallel_stream.common.HeavyJob 클래스를 활용하여
     * 1 ~ 8 의 요소를 각각 10을 곱한 후, 누적 합을 구하는 작업이다. (각각 10 을 곱할 때, 1초가 걸린다.)
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        long start = System.currentTimeMillis();




        
        // 1. Fork, 전체 하나의 작업을 2개의 작업으로 분리 및 작업 생성
        SumTask sumTask1 = new SumTask(1, 4);
        SumTask sumTask2 = new SumTask(5, 8);


        // 2. Execute, 분리된 작업을 실행
        Future<Integer> task1Result = executorService.submit(sumTask1);
        Future<Integer> task2Result = executorService.submit(sumTask2);


        // 3. Join, 처리된 작업을 병합
        int result = task1Result.get() + task2Result.get(); // blocking





        long end = System.currentTimeMillis();
        MyThreadLog.threadLog("elapsed = " + (end - start) + "ms, result = " + result);

        executorService.shutdown();
    }

    private static class SumTask implements Callable<Integer> {

        private int startValue;
        private int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() throws Exception {

            MyThreadLog.threadLog("sum task start");

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                int calculated = HeavyJob.heavyTask(i); // 1초 소요
                sum += calculated;
            }

            MyThreadLog.threadLog("sum task end");

            return sum;
        }
    }
}
