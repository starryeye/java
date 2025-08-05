package concurrent.sub6_executor.sub4_future_cancel;

import java.util.concurrent.*;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class FutureCancel {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1);

        Future<String> future = es.submit(new MyTask());
        threadLog("submit() called, future state : " + future.state());

        // 작업 완료 되기 전에 작업을 취소 시켜본다.
        mySleep(3000);
        future.cancel(true); // 작업 cancel 호출,  작업 실행 스레드에 인터럽트 예외가 발생되며 작업이 중단된다. (상태도 CANCELLED 로 변경됨)
//        future.cancel(false); // 작업 cancel 호출, 작업 실행 스레드는 작업을 계속한다. (상태만 CANCELLED 로 변경됨)
        threadLog("cancel() called, future state : " + future.state());

        try {
            threadLog("future result : " + future.get()); // CANCELLED 상태인 future::get 호출 시, CancellationException 발생함

        } catch (CancellationException e) { // CancellationException 는 런타임 예외(언캐치)
            threadLog("CancellationException caught..");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        es.shutdown();
    }

    private static class MyTask implements Callable<String> {

        @Override
        public String call() {
            try {
                for (int i = 0; i < 10; i++) { // 10초 후 작업 완료
                    threadLog("task running.. " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                threadLog("InterruptedException caught..");
                return "Interrupted!";
            }

            return "Completed!";
        }
    }
}
