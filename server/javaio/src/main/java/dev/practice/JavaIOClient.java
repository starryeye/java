package dev.practice;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class JavaIOClient {

    @SneakyThrows
    public static void main(String[] args) {

        try (Socket socket = new Socket()) { //try-with-resources

            socket.connect(new InetSocketAddress("localhost", 8080)); // 연결 시도, Blocking

            OutputStream out = socket.getOutputStream(); // server 로 데이터를 보내기 위한 stream
            String requestBody = "I am Client";
            out.write(requestBody.getBytes());
            out.flush();

            InputStream in = socket.getInputStream(); // server 의 데이터를 받기 위한 stream
            byte[] responseBytes = new byte[1024];
            in.read(responseBytes);
            log.info("response={}", new String(responseBytes).trim());
        }
    }
}
