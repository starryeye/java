package dev.practice.basic.sub7_practice.chat.server;

import dev.practice.basic.sub7_practice.chat.server.command.CommandHandlerMapping;
import dev.practice.basic.sub7_practice.chat.server.session.Session;
import dev.practice.basic.sub7_practice.chat.server.session.SessionManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class Server {

    private final int port;
    private final SessionManager sessionManager;
    private final CommandHandlerMapping commandHandlerMapping;

    private final ServerSocket serverSocket;

    public Server(int port, SessionManager sessionManager, CommandHandlerMapping commandHandlerMapping) throws IOException {
        this.port = port;
        this.sessionManager = sessionManager;
        this.commandHandlerMapping = commandHandlerMapping;
        this.serverSocket = new ServerSocket();
    }

    public void start() throws IOException {
        threadLog("server start");
        serverSocket.bind(new InetSocketAddress(port)); // todo, bind 처음, 설명 추가할 것
        threadLog("ServerSocket bind.. : " + serverSocket);

        addShutdownHook();
        run();
    }

    private void addShutdownHook() {
        ShutdownHook target = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(target, "shutdown"));
    }

    private void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept(); // blocing
                threadLog("socket accepted: " + socket);

                Session session = new Session(socket, commandHandlerMapping, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            threadLog("ServerSocket will be closed soon.. : " + e);
        }
    }

    static class ShutdownHook implements Runnable {
        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {
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
                threadLog("e = " + e);
            }

            threadLog("shutdownHook end");
        }
    }
}
