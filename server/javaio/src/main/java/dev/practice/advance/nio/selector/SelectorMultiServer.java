package dev.practice.advance.nio.selector;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// [8]
@Slf4j
public class SelectorMultiServer {

    /**
     * Java NIO 기반의 서버
     * - SelectorServer 에서 변형함.
     * <p>
     * 0.2 ~ 0.4 초 걸림.
     * - 요청을 처리하는 메인 스레드는 muliplexing 기법을 사용하여 이벤트를 기반으로 처리 중이다. (SelectorServer 도 마찬가지)
     *      - accept, read 에 해당하는 이벤트를 하나의 selector 로 처리
     * - 응답을 생성해내는 로직에 10 ms 의 외부 API blocking 이 존재하는데.. 이를 별도의 스레드로 분기하여 처리 (비동기, non-blocking)
     * - JavaAIONonBlockingMultiServer 와 비슷한 성능
     */

    private static ExecutorService executorService = Executors.newFixedThreadPool(50);

    @SneakyThrows
    public static void main(String[] args) {

        log.info("start server");
        try (ServerSocketChannel serverSocket = ServerSocketChannel.open();
             Selector selector = Selector.open(); // selector 생성
        ) {

            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                selector.select();
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

                while (selectionKeys.hasNext()) {
                    SelectionKey key = selectionKeys.next();
                    selectionKeys.remove();

                    if (key.isAcceptable()) {
                        SocketChannel clientSocket = ((ServerSocketChannel) key.channel()).accept();
                        clientSocket.configureBlocking(false);

                        clientSocket.register(selector, SelectionKey.OP_READ);

                    } else if (key.isReadable()) {
                        SocketChannel clientSocket = (SocketChannel) key.channel();

                        handleRequest(clientSocket);
                        sendResponse(clientSocket);
                    }
                }
            }
        }
    }

    @SneakyThrows
    private static void handleRequest(SocketChannel clientSocket) {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        clientSocket.read(requestByteBuffer);

        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("request={}", requestBody);
    }

    @SneakyThrows
    private static void sendResponse(SocketChannel clientSocket) {

        CompletableFuture.runAsync(() -> { // 외부 API 요청으로 인한 지연을 별도의 스레드로 처리한다.

            try {
                Thread.sleep(10); // 외부 API 요청으로 인한 지연 가정

                ByteBuffer responseByteBuffer = ByteBuffer.wrap("I am Server".getBytes());
                clientSocket.write(responseByteBuffer);

                clientSocket.close();
            } catch (Exception e) { /* do nothing */ }
        }, executorService);
    }
}
