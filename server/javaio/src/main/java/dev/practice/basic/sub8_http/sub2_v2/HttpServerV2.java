package dev.practice.basic.sub8_http.sub2_v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class HttpServerV2 {

    private final int port;

    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public HttpServerV2(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        threadLog("serverSocket created: " + serverSocket);

        while (true) {
            Socket socket = serverSocket.accept();
            threadLog("socket accepted: " + socket);

            threadPool.submit(new HttpRequestHandlerV2(socket));
        }
    }
}
