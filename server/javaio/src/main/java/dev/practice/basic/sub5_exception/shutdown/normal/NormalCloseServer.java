package dev.practice.basic.sub5_exception.shutdown.normal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class NormalCloseServer {

    /**
     * 정상 종료 흐름 예시 (server)
     */

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(8080);

        Socket socket = serverSocket.accept();
        threadLog("socket accepted : " + socket);


        Thread.sleep(3000); // client 의 요청과 server 의 응답이 이루어졌다고 가정

        socket.close(); // server 가 client 로 FIN 패킷 전달
        threadLog("socket closed : " + socket + ", isClosed : " + socket.isClosed());
    }
}
