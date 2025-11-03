package parallel_stream.sub1_heavy_job_execute.sub2_stream_api_parallel;

import parallel_stream.common.HeavyJob;
import parallel_stream.util.MyThreadLog;

import java.util.stream.IntStream;

public class ByStreamParallel {

    /**
     * 무거운 여러 개의 작업을..
     * multi 스레드(Stream api, 병렬 스트림)로 처리해본다.
     *
     * 결과
     *      8개의 작업은 8개의 스레드(common pool thread 7개, main thread 1개)에 각각 할당되어 수행됨.
     *      약 1 초가 걸림.
     *
     * BySingleThread 코드에서
     * parallel() 메서드만 추가됨.
     *
     * Stream API 에서 parallel() 메서드를 사용하면..
     *      Fork/Join common pool 이 사용되어 각 요소의 작업이 병렬적으로 수행된다.(Fork, Execute, Join)
     *      main thread 도 작업에 참여한다.
     */

    public static void main(String[] args) {
        long start = System.currentTimeMillis();


        // heavy job
        int result = IntStream.rangeClosed(1, 8)
                .parallel() // 병렬 처리
                .map(HeavyJob::heavyTask) // 1초 소요
                .reduce(0, (a, b) -> a + b);// sum() 과 동일




        long end = System.currentTimeMillis();
        MyThreadLog.threadLog("elapsed = " + (end - start) + "ms, result = " + result);
    }
}
