package dev.practice.reactor.tcp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TcpEventHandler implements EventHandler{

    /**
     * accept 이후 실제로 client 가 request 를 보내는 것을 처리한다. (TCP, 단순 문자열)
     * 기존 SelectorMultiServer 에서 read 이벤트를 처리함에 해당함
     * read 이벤트가 들어오면 처리한다.
     */


    private final Selector selector;
    private final SocketChannel clientSocket;

    private final ExecutorService executorService = Executors.newFixedThreadPool(50); // 외부 API 지연 처리를 위한 별도의 스레드

    @SneakyThrows
    public TcpEventHandler(Selector selector, SocketChannel socketChannel) {
        this.selector = selector;
        this.clientSocket = socketChannel;

        this.clientSocket.configureBlocking(false);

        // read 이벤트를 등록하며.. reader(TcpEventHandler) 를 같이 넘긴다.
        this.clientSocket.register(this.selector, SelectionKey.OP_READ).attach(this);
    }

    @Override
    public void handle() {

        // read 이벤트를 처리할 때 호출되는 메서드 이므로 read 반환값이 null 아님이 보장됨
        handleRequest(this.clientSocket);

        sendResponse(this.clientSocket);
    }

    @SneakyThrows
    private void handleRequest(SocketChannel clientSocket) {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        clientSocket.read(requestByteBuffer);

        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("request={}", requestBody);
    }

    @SneakyThrows
    private void sendResponse(SocketChannel clientSocket) {

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
