package dev.practice.io;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class JavaIOSingleServer {

    // 요청 하나만을 처리하고 종료되는 서버이다.

    @SneakyThrows
    public static void main(String[] args) {

        log.info("start server");
        try(ServerSocket serverSocket = new ServerSocket()) { // try-with-resources

            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            Socket clientSocket = serverSocket.accept();// client 와 연결될 때 까지 blocking

            byte[] requestBytes = new byte[1024];
            InputStream in = clientSocket.getInputStream(); // client 의 데이터를 받기 위한 stream
            in.read(requestBytes);
            log.info("request={}", new String(requestBytes).trim());

            OutputStream out = clientSocket.getOutputStream(); // client 로 데이터를 보내기 위한 stream
            String response = "I am Server";
            out.write(response.getBytes());
            out.flush();
        }
        log.info("end server");
    }
}
