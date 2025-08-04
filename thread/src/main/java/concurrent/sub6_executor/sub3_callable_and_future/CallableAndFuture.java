package concurrent.sub6_executor.sub3_callable_and_future;

import java.util.Random;
import java.util.concurrent.*;

import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class CallableAndFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         * Executor::execute() 는 Runnable 만 파라미터로 허용한다..
         * ExecutorService::submit() 는 파라미터로 Runnable 을 넣어줄 수 도 있지만, Callable 을 넣고 Future 를 반환 받을 수 도 있다.
         *
         * Callable..
         *      Runnable 은 반환값이 void 로 없고, throws 키워드가 선언되어있지 않으므로 외부에서 체크예외를 반환받아 처리할 수 없다.
         *      이를 위해 Callable 이 만들어졌다.
         */

        ExecutorService es = Executors.newFixedThreadPool(1);
        // Util 간편 메서드로, 내부에 들어가면 UsingAndFlow 에서 본 생성자가 보인다.


        Future<Integer> future = es.submit(new MyCallable());
        // 메인 스레드가 ExecutorService 의 BlockingQueue 에 작업(Callable) 을 넣고(생산) Future 객체를 즉시 반환 받는다.


        Integer result = future.get();
        // 메인 스레드는 ExecutorService 의 스레드가 작업(MyCallable)을 완료하고 결과를 반환할때 까지 WAITING 상태로 대기한다.


        threadLog("result: " + result);

        es.shutdown();
    }

    private static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            threadLog("MyCallable::call");
            mySleep(2000);

            int created = new Random().nextInt(10);
            threadLog("created: " + created);
            return created;
        }
    }
}
