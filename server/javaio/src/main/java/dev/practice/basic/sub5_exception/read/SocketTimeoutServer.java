package dev.practice.basic.sub5_exception.read;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTimeoutServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        /**
         * server thread 는 client 와 TCP 연결 이후, TIMED_WAITING 상태로 아무것도 하지 않는다.
         */

        ServerSocket serverSocket = new ServerSocket(8080);

        Socket socket = serverSocket.accept();

        Thread.sleep(10000000);
    }
}
