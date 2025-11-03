package parallel_stream.sub1_heavy_job_execute.sub1_fork_join_pool.sub1_by_fork_join_pool;

import parallel_stream.common.HeavyJob;
import parallel_stream.util.MyThreadLog;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ByForkJoinPool {

    /**
     * sub0_old_way 에서는 ExecutorService 를 통해 여러 개의 작업을
     * multi thread pool 을 이용하여 처리해보았다.
     *
     * 하지만, 여러 개의 작업 묶음이 다양한 종류로 많다고 생각해본다면..
     * 아래의 작업들을 공통화 할 수 있을 것 같다.
     * 1. Fork, 매번 작업 분할을 함
     * 2. Execute, 매번 분할한 작업을 직접 실행하며, 쓰레드 풀을 관리
     * 3. Join, 매번 실행한 결과를 병합함
     *
     *
     * 그래서 등장한게 ForkJoinPool (Fork/Join 프레임워크) 이다.
     * ForkJoinPool 에 대해 알아본다.
     *
     * 멀티코어 프로세서를 효율적으로 활용하기 위한 병렬 처리 프레임워크
     * java.util.concurrent 패키지에서 제공
     * 분할 정복(divide and conquer) 전략
     *      큰 작업을 작은 단위의 작업으로 재귀적 분할(fork)
     *      작은 단위 작업의 결과를 합쳐(join) 최종 결과로 병합
     *      멀티 코어 환경에서 작업을 효율적으로 분산 처리하는데 의의
     * 작업 훔치기(work stealing) 알고리즘
     *      각 스레드는 자신의 작업 큐(DEQUE)를 가짐
     *          자기 자신의 작업 큐에 작업을 보관하거나 꺼낼때 A 쪽에서 했다면, 다른 스레드가 작업을 훔칠때는 B 에서 하여 경합을 최소화 한다.
     *      현재 작업이 없는 스레드는 다른 스레드 작업 큐의 작업을 훔쳐와서 대신 처리
     *      부하 균형을 자동으로 맞추어 유휴 스레드를 최소화하는데 의의
     * 주요 클래스
     *      ForkJoinPool
     *          Fork/Join 작업을 실행하는 특수한 ExecutorService thread pool
     *          작업스케줄링 및 스레드 관리 역할
     *          cpu core 갯수 만큼 스레드 생성(default)
     *          대표 메서드
     *              public <T> T invoke(ForkJoinTask<T> task)
     *                  ForkJoinPool 에 파라미터로 제공한 task 를 수행하도록 함.
     *                  동기, blocking 방식의 호출
     *                  참고.
     *                      최초에 task 는 임의의 스레드 작업 큐에 할당되지 않고,
     *                      외부의 작업들을 받는 특별한 작업 큐에 들어가고 "외부 작업 큐" 에 존재하는 작업들은 유휴 스레드들이 작업을 훔쳐간다.
     *              public void execute(ForkJoinTask<?> task)
     *                  ForkJoinPool 에 파라미터로 제공한 task 를 수행하도록 함.
     *                  비동기, non-blocking 방식의 호출
     *      ForkJoinTask
     *          Fork/Join 작업의 기본 추상 클래스
     *          Future 를 구현
     *          기본 제공 추상 클래스 (개발자는 이를 구현하여 사용)
     *              RecursiveTask<V>
     *                  결과를 반환
     *              RecursiveAction
     *                  결과를 반환하지 않는 작업 (Future<Void> 를 구현)
     *          대표 메서드
     *              public final ForkJoinTask<V> fork()
     *                  해당 task 를 실행 스레드의 작업 큐에 담는다.
     *                  여유 있는 다른 스레드가 work stealing 에 의해 대신 처리해 줄 것을 기대함
     *              public final V join()
     *                  해당 task 의 실행이 완료될 때까지 대기하고 해당 task 의 결과를 반환 받음
     *                  미완료 시, 호출 스레드가 work-stealing 에 참여 가능해짐
     *              protected abstract V compute()
     *                  this task 를 어떻게 처리할 것인지에 대한 로직
     *                  fork / join 에 대한 호출이 담겨있다.
     *                  ForkJoinTask 추상 클래스의 필수 구현 메서드 (분할 정복 로직)
     *
     * 참고
     * 실무에서는 Fork/Join 프레임워크를 직접 다루는 일은 드물다.
     *
     *
     * 아래 예제는 ByExecutorService 와 동일하게
     * 총 8개의 작업을 4개의 작업 단위(Threshold) 로 2개의 작업 묶음으로 나누었고 2개의 스레드가 작업을 하여 약 4초가 걸린다.
     * (thread pool 의 thread 갯수 자체는 8 개가 생성되긴함)
     */

    public static void main(String[] args) {

        int thisComputerAvailableProcessors = Runtime.getRuntime().availableProcessors();
        MyThreadLog.threadLog("this computer cpu = " + thisComputerAvailableProcessors);

        // ForkJoinPool 생성, thread pool 갯수 8 개로 설정
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);

        // 작업 생성
        List<Integer> taskList = IntStream.rangeClosed(1, 8)
                .boxed()
                .toList();
        MyThreadLog.threadLog("[given task list] " + taskList);


        // 작업 시작
        long start = System.currentTimeMillis();



        Integer result = forkJoinPool.invoke(new SumTask(taskList)); // 동기, blocking
        forkJoinPool.shutdown();



        long end = System.currentTimeMillis();
        MyThreadLog.threadLog("elapsed = " + (end - start) + "ms, result = " + result);
        MyThreadLog.threadLog("forkJoinPool = " + forkJoinPool); // forkJoinPool 의 스레드 갯수와 work stealing 이 몇회 일어났는지 출력함.

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
