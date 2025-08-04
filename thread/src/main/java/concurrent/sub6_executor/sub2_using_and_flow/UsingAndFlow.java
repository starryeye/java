package concurrent.sub6_executor.sub2_using_and_flow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.MyExecutorLog.executorLog;
import static util.MyThreadLog.threadLog;
import static util.MyThreadUtils.mySleep;

public class UsingAndFlow {

    /**
     * Executor, AutoCloseable 을 상속한게 ExecutorService interface 이고, ExecutorService 를 구현한 대표 객체가 ThreadPoolExecutor 이다.
     *
     * ThreadPoolExecutor 생성자
     *      corePoolSize : 스레드 default 갯수
     *      maximumPoolSize : 스레드 최대 갯수
     *      keepAliveTime : default 갯수를 초과해서 만들어진 스레드가 작업 없이 WAITING 상태로 대기하는 최대 시간 (대기시간 이후 제거 됨)
     *      workQueue : 스레드(소비자)가 아직 처리하지 못한 작업들이 대기할 차단 큐
     */

    public static void main(String[] args) {

        // ---------------------단계 구분----------------------------
        // ThreadPoolExecutor 를 생성만 해둔 상태에서는 thread 는 생성이 되지 않는다.

        ExecutorService executorService = new ThreadPoolExecutor(
                2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        executorLog(executorService, "init");               // size=0, activeCount=0, queuedTaskCount=0, completedTaskCount=0




        // ---------------------단계 구분----------------------------
        // 메인 스레드가 ThreadPoolExecutor 의 BlockingQueue 에 작업을 4개 넣는다. (생산)
        // 최초 작업이 들어오고 thread pool 에 스레드가 2개(corePoolSize) 생성된다.
        // 생성된 스레드는 즉시 BlockingQueue 에 작업을 처리하기 위해 접근한다.
        // thread pool 의 스레드 2개가 BlockingQueue 에 접근하여 락 획득 과정을 거친 후, 작업 2개를 얻고 락 반납한다. (소비)
        // 스레드는 얻은 작업을 수행한다.

        executorService.execute(new MyTask("task 1", 1000));
        executorService.execute(new MyTask("task 2", 1000));
        executorService.execute(new MyTask("task 3", 1000));
        executorService.execute(new MyTask("task 4", 1000));
        executorLog(executorService, "tasks executed");     // size=2, activeCount=2, queuedTaskCount=2, completedTaskCount=0

        // 참고로 이 단계의 로그(activeCount=2)가 찍히고 task 1, task 2 start 로그가 찍히는게 이상해 보일수 있는데.. thread active 가 되어야 task 를 수행하므로 당연하다.





        // ---------------------단계 구분----------------------------
        // 스레드는 처리 중인 작업을 완료하면, 이어서 BlockingQueue 에 락획득 과정을 거친 후, 남은 작업 2개를 얻고 락을 반납한다. (스레드 재사용)
        // 스레드는 얻은 작업을 수행한다.

        mySleep(1500);
        executorLog(executorService, "tasks running..");    // size=2, activeCount=2, queuedTaskCount=0, completedTaskCount=2






        // ---------------------단계 구분----------------------------
        // 스레드는 처리 중인 작업을 완료하면, 이어서 BlockingQueue 에 락획득 과정을 거친 후, 남은 작업이 있는지 봤는데 없다.
        //      임계 영역내의 스레드는 락을 반납하고 "대기 스레드 큐" 에 진입한다. (RUNNABLE -> WAITING) (thread pool 에 스레드 반납된다라고 표현)

        mySleep(1500);
        executorLog(executorService, "tasks finished");     // size=2, activeCount=0, queuedTaskCount=0, completedTaskCount=4







        // ---------------------단계 구분----------------------------
        // 메인 스레드가 ThreadPoolExecutor 의 shutdown 메서드를 호출
        executorService.shutdown();
        mySleep(500); // 셧다운 시간 확보
        executorLog(executorService, "thread pool closed"); // size=0, activeCount=0, queuedTaskCount=0, completedTaskCount=4

    }

    private static class MyTask implements Runnable {

        private final String taskName;
        private final int sleepTimeMills;

        public MyTask(String taskName, int sleepTimeMills) {
            this.taskName = taskName;
            this.sleepTimeMills = sleepTimeMills;
        }

        @Override
        public void run() {
            threadLog(taskName + " start");
            mySleep(sleepTimeMills);
            threadLog(taskName + " finish");
        }
    }
}
