package dev.practice.basic.sub2_v1.test;

import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class AcceptStateDemo {

    /**
     * ServerSocket::accept() 를 호출한 스레드는 RUNNABLE 상태로 표시 된다.
     *      RUNNABLE 상태이지만, CPU 소모하지 않고 WAITING 상태 처럼 코드상 blocking 된다.
     */

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(0); // 임의 포트
        int port = server.getLocalPort();

        Thread acceptor = new Thread(() -> {
            try (server) {
                System.out.println("[acceptor] before accept()");
                Socket s = server.accept(); // 여기서 블로킹 (I/O wait)
                System.out.println("[acceptor] after accept(): " + s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "acceptor");

        // 상태 모니터링 스레드
        Thread monitor = new Thread(() -> {
            try {
                while (true) {
                    Thread.State st = acceptor.getState();
                    System.out.println("[monitor] acceptor state = " + st);
                    if (st == Thread.State.TERMINATED) break;
                    sleep(200);
                }
            } catch (InterruptedException ignored) {}
        }, "monitor");

        acceptor.start();
        monitor.start();

        // 조금 있다가 클라이언트 연결 시도 (accept 해제)
        sleep(1000);
        try (Socket client = new Socket("127.0.0.1", port)) {
            System.out.println("[client] connected");
        }

        acceptor.join();
        monitor.join();
    }
}
