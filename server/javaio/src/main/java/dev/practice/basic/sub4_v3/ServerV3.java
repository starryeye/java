package dev.practice.basic.sub4_v3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class ServerV3 {

    static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        threadLog("server start");

        SessionManagerV3 sessionManager = new SessionManagerV3();
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        threadLog("serverSocket created: " + serverSocket);

        // ShutdownHook 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown"));

        try {
            while (true) {
                Socket socket = serverSocket.accept(); // blocking
                threadLog("socket accepted: " + socket);

                SessionV3 session = new SessionV3(socket, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            threadLog("serverSocket closed.. : " + e);
        }

    }

    static class ShutdownHook implements Runnable {
        private final ServerSocket serverSocket;
        private final SessionManagerV3 sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManagerV3 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            threadLog("shutdownHook start");

            try {
                sessionManager.clear();
                serverSocket.close();

                Thread.sleep(1000); // 자원 정리 대기
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }

            threadLog("shutdownHook end");
        }
    }
}
