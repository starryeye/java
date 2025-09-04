package dev.practice.basic.sub3_v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class ServerV2 {

    static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        threadLog("server start");

        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        threadLog("serverSocket created: " + serverSocket);

        /**
         * client 가 server 로 TCP 연결을 하면, ServerV2 객체는 serverSocket.accept() 에서 벗어나며 Socket 객체를 생성하고
         * 새로운 스레드를 생성하여 해당 스레드에게 client 와 데이터를 주고 받는 책임을 넘긴다.
         * main 스레드는 다시 다음 client 의 연결이 들어올 때 까지 serverSocket.accept() 에서 blocking 된다.
         */
        while (true) {
            Socket socket = serverSocket.accept(); // blocking
            threadLog("socket accepted: " + socket);

            SessionV2 session = new SessionV2(socket);
            Thread thread = new Thread(session);
            thread.start();
        }

    }
}
