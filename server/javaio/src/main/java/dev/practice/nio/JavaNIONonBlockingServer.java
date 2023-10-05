package dev.practice.nio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
public class JavaNIONonBlockingServer {

    /**
     * Java NIO 기반의 서버
     * - non blocking 으로 동작
     */

    @SneakyThrows
    public static void main(String[] args) {

        log.info("start server");
        try(ServerSocketChannel serverSocket = ServerSocketChannel.open()) {

            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            /**
             * non-blocking 으로 동작하게된다.
             * accept 에서 client 에 연결이 되지 않으면 null 을 반환 받고 다음 라인으로 넘어간다.
             */
            serverSocket.configureBlocking(false); // non-blocking 으로 동작하는 옵션

            while (true) {

                SocketChannel clientSocket = serverSocket.accept();
                if (clientSocket == null) { // clientSocket 이 accept 될 때 까지 sleep
                    Thread.sleep(100);
//                    log.info("server wait accept..");
                    continue; // null 이면 아래 로직 스킵
                }

                ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
                while(clientSocket.read(requestByteBuffer) == 0) { // non-blocking 모드가 되면 read 가 반드시 값을 바로 준다는 보장이 없다..
                    log.info("server reading..");
                }
                requestByteBuffer.flip();
                String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
                log.info("request={}", requestBody);



                Thread.sleep(10); // 외부 API 요청으로 인한 지연 가정


                ByteBuffer responseByteBuffer = ByteBuffer.wrap("I am Server".getBytes());
                clientSocket.write(responseByteBuffer);

                clientSocket.close();
            }
        }
    }
}
