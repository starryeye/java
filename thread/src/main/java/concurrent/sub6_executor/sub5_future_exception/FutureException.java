package concurrent.sub6_executor.sub5_future_exception;

import java.util.concurrent.*;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class FutureException {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1);

        Future<String> future = es.submit(new MyTask());
        threadLog("submit() called, future state : " + future.state());

        mySleep(3000);

        try {
            threadLog("future result : " + future.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) { // 작업 내부에서 발생한 예외는 future::get() 을 호출한 스레드에게 ExecutionException 으로 감싸져서 전달된다.

            threadLog("ExecutionException : " + e);
            threadLog("cause(raised inside MyTask) : " + e.getCause()); // IllegalStateException("MyTask Exception ..") 이다.
            threadLog("future state : " + future.state());

//            e.printStackTrace();
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

                    if (i == 5) {
                        throw new IllegalStateException("MyTask Exception .."); // 5번째 작업 시, 예외 발생 (현재 catch 되지 않고 있음)
                    }
                }
            } catch (InterruptedException e) {
                threadLog("InterruptedException caught..");
                return "Interrupted!";
            }

            return "Completed!";
        }
    }
}
