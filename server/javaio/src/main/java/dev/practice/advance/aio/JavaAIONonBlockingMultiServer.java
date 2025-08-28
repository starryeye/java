package dev.practice.advance.aio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

// [6] 혼자서 만들어봄..
@Slf4j
public class JavaAIONonBlockingMultiServer {

    /**
     * Java AIO (NIO2) 기반의 서버
     * - non blocking 으로 동작
     *
     * Java AIO 는 callback 과 future 를 지원하므로..
     * 비동기 프로그래밍에 있어서 사용 편의성이 증대 되었다. 여기서는 callback 을 이용하여 동기적 코드(accept 이후, read 이후)를 비동기로 돌렸다.
     *
     * Java NIO 와 비교하여.. 사용되는 클래스들이 앞에 prefix 로 Asynchronous 가 붙는다.
     *
     *
     * todo 무슨 이유 일까요..
     *  - 이번엔 0.2 ~ 0.4 초로 엄청나다...
     *      - 혹시.. JavaNIONonBlockingMultiServer 에서 accept 를 어찌보면 동기적으로 할당을 기다리면서 sleep 하는게 있는데 그거 때문인가..
     */

    private static AtomicInteger count = new AtomicInteger();

    @SneakyThrows
    public static void main(String[] args) {

        log.info("start server");

        try(AsynchronousServerSocketChannel serverSocket = AsynchronousServerSocketChannel.open()) {

            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            serverSocket.accept(null, new CompletionHandler<>() {
                @Override
                public void completed(AsynchronousSocketChannel clientSocket, Object attachment) {

                    serverSocket.accept(null, this); // 다음 요청을 받기 위함... 이렇게 하여 while 문이 없어짐.

                    ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);

                    clientSocket.read(requestByteBuffer, null, new CompletionHandler<>() {
                        @SneakyThrows
                        @Override
                        public void completed(Integer result, Object attachment) {
                            requestByteBuffer.flip();
                            CharBuffer requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer);
                            log.info("request={}", requestBody);

                            Thread.sleep(10); // 외부 API 요청으로 인한 지연 가정

                            String response = "I am server";
                            ByteBuffer responseByteBuffer = ByteBuffer.wrap(response.getBytes());
                            clientSocket.write(responseByteBuffer);
                            clientSocket.close();
                            log.info("end client");

                            int c = count.incrementAndGet();
                            log.info("request count = {}", c);
                        }

                        @Override
                        public void failed(Throwable exc, Object attachment) { /* do nothing */ }
                    });
                }

                @Override
                public void failed(Throwable exc, Object attachment) { /* do nothing */ }
            });

            Thread.sleep(100_000); // accept 가 future 를 바로 반환하고 non-blocking 으로 동작하므로 종료를 방지한다..
            log.info("end server");
        }
    }
}
