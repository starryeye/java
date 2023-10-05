package dev.practice.nio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class JavaNIONonBlockingMultiServer {

    /**
     * Java NIO 기반의 서버
     * - non blocking 으로 동작
     */

    private static AtomicInteger count = new AtomicInteger();
    private static ExecutorService executorService = Executors.newFixedThreadPool(50);

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

                /**
                 * "main 입장에서 보면 아래 로직을 호출하는 것이 blocking 을 발생 시키고 있는 것이다."
                 * -> 해결...
                 * blocking 되는 로직을 main 스레드에서 비동기 non blocking 처리하였다.(다른 스레드로 처리)
                 * 그러면..
                 * main 스레드는 다음 요청을 받을 수 있는 준비 상태가 바로 될 것이다..
                 *
                 * 스레드가 참 신기하네..
                 * 분기된 스레드는 clientSocket 를 온전히 가지고.. close 까지 잘 처리할 것이고..
                 * 메인 스레드는 기존 요청은 신경쓰지 않고 다음 요청을 처리할 수 있으니..
                 *
                 * 결과에 관심이 없다. 결과는 callee(다른 스레드) 가 처리한다. -> 비동기
                 * 다른 스레드로 분기되어 처리되어 caller(main) 이 자기 할 일을 할 수 있다. -> non-blocking
                 *
                 * JavaIOMultiClient 로 수행결과.. 12초 걸리던것이 2초 정도로 크게 단축되었다.
                 */
                CompletableFuture.runAsync(() -> {
                    handleRequest(clientSocket);
                }, executorService);

            }
        }
    }

    @SneakyThrows
    private static void handleRequest(SocketChannel clientSocket) {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        while(clientSocket.read(requestByteBuffer) == 0) {
            log.info("server reading..");
        }
        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("request={}", requestBody);


        Thread.sleep(10); // 외부 API 요청으로 인한 지연 가정


        ByteBuffer responseByteBuffer = ByteBuffer.wrap("I am Server".getBytes());
        clientSocket.write(responseByteBuffer);

        clientSocket.close();

        int c = count.incrementAndGet();
        log.info("request count = {}", c);
    }
}
