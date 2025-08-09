package concurrent.sub6_executor.sub8_prestart_all_core_threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyExecutorLog.executorLog;

public class PrestartAllCoreThreads {

    /**
     * ThreadPoolExecutor 에는 core thread 를 미리 생성 해두는 기능을 제공한다.
     * prestartAllCoreThreads()
     */

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1000);
        executorLog(es);

        ((ThreadPoolExecutor) es).prestartAllCoreThreads(); // core thread 를 미리 생성할 수 있다.
        executorLog(es);

        es.shutdown();
    }
}
