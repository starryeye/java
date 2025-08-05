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
         *
         * Future..
         *      ExecutorService 를 사용하는 스레드(Caller, 여기서는 main thread)가 submit 메서드를 이용하여 작업을 제출(생산)하면..
         *      ExecutorService 는 Caller 에게 해당 작업의 결과를 받을 수 있는 Future 객체를 즉시 리턴한다.
         *      Caller 가 Future::get() 을 호출하면, 
         *          ExecutorService 내부 소비자 스레드(Callee)가 제출된 작업을 수행하고 작업 결과를 Future 에 넣고 완료처리할 때 까지 WAITING 상태로 대기하게 된다.
         *          Callee 가 Future 에 결과를 넣고 Future 객체 상태를 완료처리한다.
         *          이후, Callee 는 Caller 스레드를 깨우게 된다. Caller 는 WAITING -> RUNNABLE 로 변경되며 결과 값을 사용할 수 있다.
         *
         * 참고
         * ExecutorService::submit 을 호출하면
         * 내부 BlockingQueue 에 적재되는 작업은 FutureTask 타입의 객체이며
         * FutureTask 는 Runnable 을 구현한 객체로, 스레드 최초 Thread::run() 메서드가 호출되면 내부에서 Callable::call() 이 호출하는 방식으로 동작한다.
         *
         *
         */

        ExecutorService es = Executors.newFixedThreadPool(1);
        // Util 간편 메서드로, 내부에 들어가면 UsingAndFlow 에서 본 생성자가 보인다.


        Future<Integer> future = es.submit(new MyCallable());
        // 메인 스레드가 ExecutorService 의 BlockingQueue 에 작업(Callable) 을 넣고(생산) Future 객체를 즉시 반환 받는다.


        Integer result = future.get();
        // 메인 스레드는 ExecutorService 의 스레드가 깨울때 까지 WAITING 상태로 대기한다. (CPU 소모 X)
        //      메인 스레드, FutureTask::awaitDone 에서 LockSupport.park() 를 호출함
        // ExecutorService 의 스레드는 작업(MyCallable)을 완료하고, 결과를 Future 에 넣고, Future 상태를 완료로 변경 후 메인 스레드를 깨운다.
        //      ExecutorService 의 스레드, FutureTask::finishCompletion 에서 LockSupport.unpark(메인스레드) 를 호출함
        // synchronous, blocking


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
