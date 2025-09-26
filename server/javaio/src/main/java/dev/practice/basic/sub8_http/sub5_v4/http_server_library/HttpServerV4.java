package dev.practice.basic.sub8_http.sub5_v4.http_server_library;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class HttpServerV4 {

    private final int port;
    private final HttpServletContainerV4 servletMapping;

    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public HttpServerV4(int port, HttpServletContainerV4 servletMapping) {
        this.port = port;
        this.servletMapping = servletMapping;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        threadLog("serverSocket created: " + serverSocket);

        while (true) {
            Socket socket = serverSocket.accept();
            threadLog("socket accepted: " + socket);

            threadPool.submit(new HttpRequestHandlerV4(socket, servletMapping));
        }
    }
}
