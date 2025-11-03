package parallel_stream.sub1_heavy_job_execute.sub1_fork_join_pool.sub2_common_pool;

import parallel_stream.common.HeavyJob;
import parallel_stream.util.MyThreadLog;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class CommonPool {

    /**
     * Java 8 에서 Fork/Join Common pool 이 도입 되었다.
     *
     * Fork/Join common pool 에 대해 알아본다.
     *      application 내에서 단일 인스턴스로 공유된다.
     *          ForkJoinPool.commonPool(); 로 호출하면, 해당 인스턴스를 받아 사용할 수 있다.
     *          JVM 이 관리하여 shutdown 할 필요 없음
     *          common pool 의 thread 는 데몬 스레드이다.
     *      병렬 처리에 편리하게 사용됨.
     *          Stream API 에서 parallel() 를 사용하면 내부적으로 common pool 이 사용됨
     *          CompletableFuture 에서 별도의 thread pool 을 지정하지 않고 비동기 처리 요청하면 common pool 이 사용됨
     *      application 이 동작하는 컴퓨터의 cpu core 수 -1 만큼의 thread 갯수로 설정된다.
     *      ForkJoinPool 을 직접 생성하여 사용할 때와 다르게 common pool 을 사용하면..
     *          작업을 요청한 스레드도 작업에 참여한다. -> 그래서 cpu core 수에서 1 을 뺀만큼 thread 갯수가 설정된 것이다.
     *
     * 참고.
     * Fork/Join common pool 의 설정은 시스템 속성으로 변경가능하다.
     * -Djava.util.concurrent.ForkJoinPool.common.parallelism={스레드 풀의 스레드 갯수}
     * System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "{스레드 풀의 스레드 갯수}");
     *
     *
     * 아래 예제는 ByForkJoinPool 와 동일한 SumTask 를 사용하여
     * 작업을 Fork, Execute, Join 하는 로직이 동일하며
     * ForkJoinPool 만 직접 생성하지 않고 common pool 을 사용하여..
     * 동작성은 동일하다.
     *      총 8개의 작업을 4개의 작업 단위(Threshold) 로 2개의 작업 묶음으로 나누었고 2개의 스레드가 작업을 하여 약 4초가 걸린다.
     *      thread pool 의 thread 갯수 자체는 7 개로 구성됨
     *
     */

    public static void main(String[] args) {

        // common pool 할당
        ForkJoinPool commonPool = ForkJoinPool.commonPool();

        int thisComputerAvailableProcessors = Runtime.getRuntime().availableProcessors();
        MyThreadLog.threadLog("this computer cpu = " + thisComputerAvailableProcessors);
        MyThreadLog.threadLog("common pool = " + commonPool); // parallelism 이 (thisComputerAvailableProcessors - 1)개 이다.



        // 작업 생성
        List<Integer> taskList = IntStream.rangeClosed(1, 8)
                .boxed()
                .toList();
        MyThreadLog.threadLog("[given task list] " + taskList);


        // 작업 시작
        long start = System.currentTimeMillis();


        /**
         * ForkJoinPool 을 직접 생성하여 사용하는 것과 다르게..
         *      직접 생성하여 사용하면, ByForkJoinPool 에서 볼 수 있듯이 ForkJoinPool 의 invoke 로 작업을 제출함.
         * ForkJoinTask 의 invoke() 를 호출해주면 Fork/Join common pool 로 처리된다.
         * 또한, 작업을 요청한 main thread 도 작업에 참여하는 것을 볼 수 있다. (그래서 work stealing 이 1회만 발생함)
         *
         * 참고.
         * main thread 를 작업에 참여시키고 싶지 않다면, fork() 를 이용하면 됨.
         */
        SumTask sumTask = new SumTask(taskList);
        Integer result = sumTask.invoke(); // 동기, blocking


        long end = System.currentTimeMillis();
        MyThreadLog.threadLog("elapsed = " + (end - start) + "ms, result = " + result);
        MyThreadLog.threadLog("common pool = " + commonPool); // common pool 의 스레드 갯수와 work stealing 이 몇회 일어났는지 출력함.
    }

    private static class SumTask extends RecursiveTask<Integer> { // RecursiveTask 를 구현함.

        private static final int THRESHOLD = 4; // 작업의 최소 단위로 해당 값 보다 작업량이 크면 작업을 나누게 된다.

        private final List<Integer> tasklist;

        public SumTask(List<Integer> tasklist) {
            this.tasklist = tasklist;
        }

        @Override
        protected Integer compute() {

            if (tasklist.size() <= THRESHOLD) { // 작업량이 적정함
                MyThreadLog.threadLog("[start process] " + tasklist);

                int sum = tasklist.stream()
                        .mapToInt(HeavyJob::heavyTask) // 1초 소요
                        .sum();

                MyThreadLog.threadLog("[end process] " + tasklist + " --> " + sum);
                return sum;
            } else { // 작업량이 적정하지 않아 분할하여 병렬 처리되도록 함

                int mid = tasklist.size() / 2;

                // 작업 분할
                List<Integer> left = tasklist.subList(0, mid);
                List<Integer> right = tasklist.subList(mid, tasklist.size());
                MyThreadLog.threadLog("[Fork] " + tasklist + " --> left = " + left + ", right = " + right);
                SumTask leftTask = new SumTask(left);
                SumTask rightTask = new SumTask(right);


                // 왼쪽 작업은 다시 자신의 작업 큐에 담는다. (여유 있는 다른 스레드가 work stealing 에 의해 대신 처리해 줄 것을 기대함)
                leftTask.fork();
                // 오른쪽 작업은 현재 스레드에서 바로 처리
                Integer rightResult = rightTask.compute(); // 동기 blocking 호출, 재귀 호출



                // 왼쪽 작업의 결과를 대기
                Integer leftResult = leftTask.join();
                // 왼쪽/오른쪽 작업 결과 병합 (재귀 병합)
                int joinSum = leftResult + rightResult;



                MyThreadLog.threadLog("[Join] (left result = " + leftResult + ", right result = " + rightResult + ") --> result = " + joinSum);
                return joinSum;
            }
        }
    }
}
