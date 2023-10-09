package dev.practice.nio.reactor.http;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class HttpEventHandler implements EventHandler {

    /**
     * read 이벤트가 들어오면 처리한다.
     *
     * 즉, 기존에는 client 에서 tcp 요청(단순 문자열)이 server 들어왔는데..
     * 이제 tcp 요청 값으로 http protocol 로 들어옴
     * 여기서 http 처리를 해주면 되겠다!
     */


    private final Selector selector;
    private final SocketChannel clientSocket;

    private final ExecutorService executorService = Executors.newFixedThreadPool(50); // 외부 API 지연 처리를 위한 별도의 스레드

    private final MessageCodec messageCodec; // Http protocol 인코딩 디코딩을 위함

    @SneakyThrows
    public HttpEventHandler(Selector selector, SocketChannel socketChannel) {
        this.selector = selector;
        this.clientSocket = socketChannel;
        this.messageCodec = new MessageCodec();

        this.clientSocket.configureBlocking(false);

        // read 이벤트를 등록하며.. reader(TcpEventHandler) 를 같이 넘긴다.
        this.clientSocket.register(this.selector, SelectionKey.OP_READ).attach(this);
    }

    @Override
    public void handle() {

        // read 이벤트를 처리할 때 호출되는 메서드 이므로 read 반환값이 null 아님이 보장됨
        String requestData = handleRequest(this.clientSocket);

        sendResponse(this.clientSocket, requestData);
    }

    @SneakyThrows
    private String handleRequest(SocketChannel clientSocket) {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        clientSocket.read(requestByteBuffer);

        // read 한 ByteBuffer 를 디코딩하여 Http 쿼리 파라미터를 만들어 리턴
        return messageCodec.decode(requestByteBuffer);
    }

    @SneakyThrows
    private void sendResponse(SocketChannel clientSocket, String requestData) {

        CompletableFuture.runAsync(() -> { // 외부 API 요청으로 인한 지연을 별도의 스레드로 처리한다.

            try {
                Thread.sleep(10); // 외부 API 요청으로 인한 지연 가정

                ByteBuffer responseByteBuffer = messageCodec.encode(requestData); // 요청 데이터를 활용하여 응답값을 생성하고 인코딩
                clientSocket.write(responseByteBuffer);

                clientSocket.close();
            } catch (Exception e) { /* do nothing */ }
        }, executorService);
    }
}
