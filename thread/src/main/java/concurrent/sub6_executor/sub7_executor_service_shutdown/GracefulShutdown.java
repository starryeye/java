package concurrent.sub6_executor.sub7_executor_service_shutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static util.MyExecutorLog.executorLog;
import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class GracefulShutdown {

    /**
     * ExecutorService 가 제공하는 shutdown, shutdownNow 메서드를 이용하여
     * graceful shutdown 을 구현해본다.
     *
     * 아래 구현한 shutdownAndAwaitTermination 은 ExecutorService 공식 API 문서에서 제안하는 방식임.
     *
     * void shutdown()
     *      non-blocking
     *      호출하면, 더이상 새로운 작업을 받지 않음
     *      처리 중이거나 blocking queue 에서 실행 대기 중인 작업들은 처리를 완료시킨다.
     *      남아있던 작업을 모두 완료시키면 종료한다.
     *
     * List<Runnable> shutdownNow()
     *      blocking
     *      호출하면, 더이상 새로운 작업을 받지 않음
     *      처리 중이던 작업은 인터럽트 발생
     *      blocking queue 에서 실행 대기 중인 작업들은 실행하지 않고 컬렉션으로 반환한다. (반환값)
     *      즉시 종료한다.
     */

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new MyTask("task 1", 1_000));
        es.execute(new MyTask("task 2", 1_000));
        es.execute(new MyTask("task 3", 1_000));
        es.execute(new MyTask("long task", 100_000)); // shutdownAndAwaitTermination 대기시간 보다 긴 것을 의도함
        executorLog(es);

        threadLog("shutdown start");
        shutdownAndAwaitTermination(es);
        threadLog("shutdown end");
        executorLog(es);
    }

    private static void shutdownAndAwaitTermination(ExecutorService es) {

        es.shutdown(); // shutdown 시도, 새로운 작업 받지 않음, 실행 중이거나 차단큐에 존재하는 작업은 완료시킨다. non-blocking

        try {
            threadLog("attempt to terminate the service normally.."); // 정상 종료 시도

            if (!es.awaitTermination(10, TimeUnit.SECONDS)) { // 10초간 정상 종료 대기, 10초가 넘어도 정상 종료되지 않으면 if 문 수행

                threadLog("timed out waiting for termination of service normally.."); // 정상 종료 실패 -> 강제 종료 시도
                es.shutdownNow(); // 강제 종료 시도, blocking

                if (!es.awaitTermination(10, TimeUnit.SECONDS)) { // 10초간 강제 종료 대기
                    threadLog("developer alert!!"); // 강제종료가 되지않아서, 개발자 알림 !!
                }
            }
        } catch (InterruptedException e) {
            es.shutdownNow(); // awaitTermination 도중 shutdownAndAwaitTermination 를 호출한 스레드가 인터럽트 발생한 경우
        }
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
