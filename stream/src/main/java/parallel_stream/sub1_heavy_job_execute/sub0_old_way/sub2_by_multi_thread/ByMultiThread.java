package parallel_stream.sub1_heavy_job_execute.sub0_old_way.sub2_by_multi_thread;

import parallel_stream.common.HeavyJob;
import parallel_stream.util.MyThreadLog;

public class ByMultiThread {

    /**
     * 무거운 여러 개의 작업을..
     * 멀티 스레드(2개의 스레드)로 처리해본다.
     *
     * 결과
     *      약 4 초가 걸림.
     *
     * 의의
     *      BySingleThread 와 비교하여..
     *      2 개의 스레드로 병렬적으로 작업을 처리하여 총 시간이 2 배로 개선됨.
     *
     *
     * 참고
     * 여기서의 무거운 작업이란..
     * parallel_stream.common.HeavyJob 클래스를 활용하여
     * 1 ~ 8 의 요소를 각각 10을 곱한 후, 누적 합을 구하는 작업이다. (각각 10 을 곱할 때, 1초가 걸린다.)
     */

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        // 1. Fork, 전체 하나의 작업을 2개의 작업으로 분리 및 작업 생성
        SumTask sumTask1 = new SumTask(1, 4);
        SumTask sumTask2 = new SumTask(5, 8);

        // 2. Execute, 분리된 작업을 실행
        Thread thread1 = new Thread(sumTask1);
        Thread thread2 = new Thread(sumTask2);
        thread1.start();
        thread2.start();

        // 3. Join, 처리된 작업을 병합
        thread1.join(); // blocking
        thread2.join(); // blocking
        int result = sumTask1.getResult() + sumTask2.getResult();


        long end = System.currentTimeMillis();
        MyThreadLog.threadLog("elapsed = " + (end - start) + "ms, result = " + result);
    }

    private static class SumTask implements Runnable {

        private int startValue;
        private int endValue;
        private int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {

            MyThreadLog.threadLog("sum task start");

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                int calculated = HeavyJob.heavyTask(i); // 1초 소요
                sum += calculated;
            }
            result = sum;

            MyThreadLog.threadLog("sum task end");
        }

        public int getResult() {
            return result;
        }
    }
}
