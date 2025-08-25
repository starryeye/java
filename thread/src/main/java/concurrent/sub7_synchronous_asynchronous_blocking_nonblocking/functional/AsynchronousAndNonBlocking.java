package concurrent.sub7_synchronous_asynchronous_blocking_nonblocking.functional;

import java.util.concurrent.*;
import java.util.function.Consumer;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class AsynchronousAndNonBlocking {

    /**
     * asynchronous, non-blocking 예시
     *
     * asynchronous
     *      Caller 는 Callee 의 결과에 관심이 없다.
     *      -> Callee 가 수행한 결과를 반환 받지도 않는다.
     *              Caller 는 Callee 가 수행한 결과에 +1 을 하고 로그를 남겨야 했지만, Callee 에게 양도함.
     * non-blocking
     *      Caller 가 Callee 를 호출한 후, Callee 가 작업하더라도 Caller 는 자유롭게 다른 작업가능 (Caller 의 제어권은 Caller 에 존재)
     *      -> getResult() 메서드를 호출하면, 그 즉시 리턴되어 Caller 는 다른 작업(스레드 종료)을 할 수 있다.
     *
     */

    private static ExecutorService es = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Caller 영역

        threadLog("caller start");

        getResult(new Consumer<Integer>() {
            @Override
            public void accept(Integer result) {
                result++;
                System.out.println(result);
            }
        });

        threadLog("caller end");
    }

    private static void getResult(Consumer<Integer> callback) {

        // Callee 영역

        try {
            es.submit(new Runnable() { // 리턴 및 체크예외 처리할게 없으므로 Runnable 사용
                @Override
                public void run() {
                    threadLog("callee start");

                    mySleep(1000);
                    int result = 100;

                    callback.accept(result);

                    threadLog("callee end");
                }
            });
        } finally {
            es.shutdown(); // 코드상 작업이 완료되기 전에 호출되는데, 작업 큐에 존재하거나 처리 중인 작업을 모두 완료하고 shutdown 된다.
        }
    }
}
