package thread.state.sub4_yield;


public class Yield {

    /**
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
        Thread t1 = new MyTask("Thread-1", methodType);
        Thread t2 = new MyTask("Thread-2", methodType);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static class MyTask extends Thread {
        private final String methodType;

        public MyTask(String name, String methodType) {
            super(name);
            this.methodType = methodType;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
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
                        Thread.yield(); // CPU 양보
                        break;
                    case "none":
                        // 아무것도 안 함
                        break;
                }
            }
        }
    }
}
