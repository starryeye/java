package dev.practice.nio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

// [3]
@Slf4j
public class JavaNIOBlockingServer {

    /**
     * Java NIO 기반의 서버
     * - blocking 으로 동작 (JavaIOServer 와 동일하게 accept 에서 blocking 된다.)
     *
     * ServerSocket 대신 ServerSocketChannel 을 이용한다.
     *
     * ServerSocketChannel 의 accept() 리턴 타입은 SocketChannel 이다.
     */

    @SneakyThrows
    public static void main(String[] args) {

        log.info("start server");
        try(ServerSocketChannel serverSocket = ServerSocketChannel.open()) { // try-with-resources

            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            while (true) {
                SocketChannel clientSocket = serverSocket.accept();// client 와 연결될 때 까지 blocking


                ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024); // buffer 단위로 읽는다.
                clientSocket.read(requestByteBuffer); // client 로 부터 데이터를 받는다.
                requestByteBuffer.flip(); // socket 으로 부터 한번 읽힌 buffer 라 flip 을 해야 우리도 읽을 수 있다.
                String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
                log.info("request={}", requestBody);


                ByteBuffer responseByteBuffer = ByteBuffer.wrap("I am Server".getBytes());
                clientSocket.write(responseByteBuffer); // client 로 데이터를 보낸다.

                clientSocket.close(); // 소켓 채널 닫음. 클라이언트와 연결 종료 (서버소켓채널을 닫는 것은 아니다.)
            }
        }
    }
}
