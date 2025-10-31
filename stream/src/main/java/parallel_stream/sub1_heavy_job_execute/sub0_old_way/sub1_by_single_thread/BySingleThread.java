package parallel_stream.sub1_heavy_job_execute.sub0_old_way.sub1_by_single_thread;

import parallel_stream.common.HeavyJob;
import parallel_stream.util.MyThreadLog;

import java.util.stream.IntStream;

public class BySingleThread {

    /**
     * 무거운 여러 개의 작업을..
     * 단일 스레드(단일 스트림)으로 처리해본다.
     *
     * 결과
     *      약 8 초가 걸림.
     *
     *
     * 참고
     * 여기서의 무거운 작업이란..
     * parallel_stream.common.HeavyJob 클래스를 활용하여
     * 1 ~ 8 의 요소를 각각 10을 곱한 후, 누적 합을 구하는 작업이다. (각각 10 을 곱할 때, 1초가 걸린다.)
     */

    public static void main(String[] args) {
        long start = System.currentTimeMillis();


        // heavy job
        int result = IntStream.rangeClosed(1, 8)
                .map(HeavyJob::heavyTask) // 1초 소요
                .reduce(0, (a, b) -> a + b);// sum() 과 동일




        long end = System.currentTimeMillis();
        MyThreadLog.threadLog("elapsed = " + (end - start) + "ms, result = " + result);
    }
}
