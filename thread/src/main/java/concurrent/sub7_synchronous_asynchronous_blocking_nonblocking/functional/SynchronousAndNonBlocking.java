package concurrent.sub7_synchronous_asynchronous_blocking_nonblocking.functional;

import java.util.concurrent.*;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class SynchronousAndNonBlocking {

    /**
     * synchronous, non-blocking 예시
     *
     * synchronous
     *      Caller 는 Callee 의 결과에 관심이 있다.
     *      -> 결과에 +1 을 하고 로그를 남김
     * non-blocking
     *      Caller 가 Callee 를 호출한 후, Callee 가 작업하더라도 Caller 는 자유롭게 다른 작업가능 (Caller 의 제어권은 Caller 에 존재)
     *      -> getResult() 메서드를 호출하면, 그 즉시 리턴되어 Caller 는 다른 작업(로그 찍기)을 할 수 있다.
     *
     */

    private static ExecutorService es = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Caller 영역

        threadLog("caller start");

        Future<Integer> future = getResult();
        while (!future.isDone()) {
            threadLog("caller waiting for result..");
            mySleep(100);
        }

        int result = future.get() + 1;
        System.out.println(result);

        threadLog("caller end");
    }

    private static Future<Integer> getResult() {

        // Callee 영역

        try {
            return es.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {

                    threadLog("callee start");

                    mySleep(1000);
                    int result = 100;

                    threadLog("callee end");
                    return result;
                }
            });
        } finally {
            es.shutdown(); // 코드상 작업이 완료되기 전에 호출되는데, 작업 큐에 존재하거나 처리 중인 작업을 모두 완료하고 shutdown 된다.
        }
    }
}
