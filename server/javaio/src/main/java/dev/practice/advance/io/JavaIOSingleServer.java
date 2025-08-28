package dev.practice.advance.io;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

// [1]
@Slf4j
public class JavaIOSingleServer {

    // 요청 하나만을 처리하고 종료되는 서버이다.

    @SneakyThrows
    public static void main(String[] args) {

        log.info("start server");
        try(ServerSocket serverSocket = new ServerSocket()) { // try-with-resources

            serverSocket.bind(new InetSocketAddress("localhost", 8080)); // 생성한 서버 소켓이 어떤 소켓인지 정의한다. (ip, port 를 정함)
            Socket clientSocket = serverSocket.accept();// client 와 연결될 때 까지 blocking

            byte[] requestBytes = new byte[1024];
            InputStream in = clientSocket.getInputStream(); // client 의 데이터를 받기 위한 stream
            in.read(requestBytes); // client 에서 데이터를 보낼때 까지 blocking. 데이터를 읽거나 예외가 발생하면 blocking 풀린다.
            log.info("request={}", new String(requestBytes).trim());

            OutputStream out = clientSocket.getOutputStream(); // client 로 데이터를 보내기 위한 stream
            String response = "I am Server";
            out.write(response.getBytes()); // 서버 OS 네트워크 버퍼에 데이터를 적재할때까지 blocking, (클라이언트에 데이터를 모두 전송할 때까지가 아니다.)
            out.flush();

            // Client 가 연결 종료하여도 Server 측에서는 자동으로 해당 소켓을 종료하지는 않는다.
            // 따라서 연결 종료됨의 시그널 (InputStream::read 메서드 호출 리턴 값 -1) 을 받으면 close 를 호출하는 편이 좋다.
//            clientSocket.close();
        }
        log.info("end server");
    }
}
