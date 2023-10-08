package dev.practice.nio.selector;

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

// [7]
@Slf4j
public class SelectorServer {

    /**
     * Java NIO 기반의 서버
     * - JavaNIONonBlockingServer 에서 변형함. busy wait 없앰
     *
     * 12 초 걸림.
     * - 서버에는 한개의 스레드만 돌고 있다.
     * - caller 입장에서 non-blocking 이지만.. 한번에 하나의 요청만 처리하기 때문..
     * - 한 요청당 10 ms 의 시간이 걸리고 1000 번을 함, 따라서 10초 이상임
     */

    @SneakyThrows
    public static void main(String[] args) {

        log.info("start server");
        try(ServerSocketChannel serverSocket = ServerSocketChannel.open();
            Selector selector = Selector.open(); // selector 생성
        ) {

            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            serverSocket.configureBlocking(false); // non-blocking 으로 동작하는 옵션, accept 할 때 없어도.. null 리턴하고 다음줄 넘어감
            serverSocket.register(selector, SelectionKey.OP_ACCEPT); // ServerSocketChannel 의 Accept 작업을 selector 에 등록

            while (true) {

                selector.select(); // selector 에 등록된 이벤트들 중.. 준비된 이벤트가 없으면 blocking
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator(); // 준비된 이벤트 목록을 뽑는다.

                while (selectionKeys.hasNext()) {
                    SelectionKey key = selectionKeys.next(); // 해당 SelectionKey 에는 어떤 채널, selector, 이벤트 의 정보가 존재
                    selectionKeys.remove();

                    if(key.isAcceptable()) { // accept 이벤트일 경우..
                        // accept 준비가 되었으므로 clientSocket 은 null 이 아님이 보장됨.
                        SocketChannel clientSocket = ((ServerSocketChannel)key.channel()).accept(); // 이벤트 대상 채널은 ServerSocketChannel 인 것이다.
                        clientSocket.configureBlocking(false); // non-blocking 으로 동작하는 옵션, read 할 때 없어도.. null 리턴하고 다음줄 넘어감

                        clientSocket.register(selector, SelectionKey.OP_READ); // SocketChannel 의 read 작업을 selector 에 등록

                    } else if (key.isReadable()) { // read 이벤트일 경우..
                        // read 준비가 되었으므로 ByteBuffer 는 null 이 아님이 보장됨.
                        SocketChannel clientSocket = (SocketChannel) key.channel(); // 이벤트 대상 채널은 SocketChannel 인 것이다.

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
        clientSocket.read(requestByteBuffer); // read 를 해도 null 이 아님이 보장됨.

        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("request={}", requestBody);
    }

    @SneakyThrows
    private static void sendResponse(SocketChannel clientSocket) {

        Thread.sleep(10); // 외부 API 요청으로 인한 지연 가정

        ByteBuffer responseByteBuffer = ByteBuffer.wrap("I am Server".getBytes());
        clientSocket.write(responseByteBuffer);

        clientSocket.close();
    }
}
