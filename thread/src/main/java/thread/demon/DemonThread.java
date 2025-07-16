package thread.demon;

import util.MyThreadLog;

import static util.MyThreadLog.threadLog;

public class DemonThread {

    /**
     * Java 에서 스레드는 유저 스레드와 데몬 스레드로 나뉜다.
     *
     * 유저 스레드
     *      프로그램의 주요 작업을 수행
     *      작업이 완료될 때 까지 프로그램이 실행된다. (모든 유저 스레드가 종료되면 JVM 종료)
     *
     * 데몬 스레드
     *      백그라운드에서 보조적인 작업을 수행
     *      모든 유저 스레드가 종료되면 데몬 스레드는 자동으로 종료
     */

    public static void main(String[] args) {
        threadLog("is start");

        Thread thread = new Thread(() -> {
            threadLog("is start");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            threadLog("is end"); // 데몬 스레드 보다 유저 스레드가 먼저 종료 되므로 이 로그는 출력되지 않음
        });
        thread.setDaemon(true); // 데몬 스레드로 설정
        thread.setName("Demon Thread");
        thread.start();

        threadLog("is end");
    }
}
