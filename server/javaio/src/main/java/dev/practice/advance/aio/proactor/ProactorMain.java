package dev.practice.advance.aio.proactor;

public class ProactorMain {

    public static void main(String[] args) {


        new Proactor(8080).run();

        // 개발자가 만든 스레드는 없는 상황임
        // 따라서.. 스레드는 AIO 의 스레드 풀인데 데몬인듯..
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
