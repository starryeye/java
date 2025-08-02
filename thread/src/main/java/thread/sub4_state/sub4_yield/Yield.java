package thread.sub4_state.sub4_yield;


public class Yield {

    /**
     * Thread::yield 메서드에 대해 알아본다.
     *
     * CPU 코어를 이용하여 어떤 스레드를 얼마나 실행할지는 OS 가 스케줄링을 통해 결정한다.
     *
     * 특정 스레드가 논리적으로 중요한 작업을 수행하는 것이 아니라서 다른 스레드에게 CPU 실행 기회를 양보하도록 만들고 싶을 때가 있을 수 있다.
     * -> 스케줄링 큐에 존재하는 다른 스레드가 CPU 코어로부터 실행될 수 있게 된다.
     *
     * Thread::yield 를 사용하면
     * 해당 메서드를 호출한 스레드는 RUNNABLE 상태를 유지한 상태로 스케줄링 큐 가장 뒤 순서로 밀린다고 생각하면 편하다.
     * 하지만, 실제로는 OS 스케줄러에 hint 만 준 것이고 스케줄러의 마음데로 실행되게 된다.
     *
     * 의의.
     * sleep 과 sleep 을 안준 중간 정도의 효과를 볼 수 있다고 보면되고,
     * 모든 스레드가 sleep 이 되면 CPU 자원을 아무도 사용하지 않을 것인데..
     * yield 를 사용하면 CPU 자원을 아무도 사용하지 않는 경우는 없앨수 있다.
     *
     *
     * 아래 예제 코드 설명 (컴퓨터 하드웨어 스팩에 따라 결과가 잘 나오지 않을 수 있음..)
     * 1. sleep 으로 실행
     * 2개의 스레드가 일정한 시간 간격으로 CPU 코어들에 의해 실행된다.
     * 상태 변경 추이
     *      RUNNABLE -> TIMED_WAITING -> RUNNABLE ...
     *
     * 2. yield 로 실행
     * 2개의 스레드가 1 개만 출력하고 양보를 하는데..
     * 실제 출력 결과를 보면 하나의 스레드가 여러번 연속 출력되기도 한다.
     * 상태 변경 추이
     *      RUNNABLE ...
     *
     * 3. sleep, yield 모두 사용하지 않고 실행
     * 확실히 yield 보다 훨씬 더 하나의 스레드가 여러번 연속 출력하는 편으로 보인다.
     * 상태 변경 추이
     *      RUNNABLE ...
     *
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== [1] Using Thread.sleep() ===");
        runThreads("sleep");

        System.out.println("\n=== [2] Using Thread.yield() ===");
        runThreads("yield");

        System.out.println("\n=== [3] Without sleep or yield ===");
        runThreads("none");
    }

    private static void runThreads(String methodType) throws InterruptedException {

        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyTask("Thread-" + i, methodType);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static class MyTask extends Thread {
        private final String methodType;

        public MyTask(String name, String methodType) {
            super(name);
            this.methodType = methodType;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                System.out.println("[" + Thread.currentThread().getName() + "] Count: " + i);

                switch (methodType) {
                    case "sleep":
                        try {
                            Thread.sleep(100); // 100ms 동안 멈춤
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "yield":
                        Thread.yield(); // CPU 양보 힌트
                        break;
                    case "none":
                        // 아무것도 안 함
                        break;
                }
            }
        }
    }
}
