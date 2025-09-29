package dev.practice.advance.nio.reactor.tcp;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// [9]
@Slf4j
public class Reactor implements Runnable{ // Reactor 는 별도의 스레드에서 동작하므로 Runnable 을 구현

    /**
     * Reactor Pattern 을 적용한 TCP 서버 구현
     * - SelectorMultiServer 을 변형함 기존의 SelectorMultiServer 는 기능이 빈약했다.(확장성이 부족함)
     * - 즉, 기능이 뭔가 추가된 건 아니고.. 객체 설계 관점에서 확장성이 증가됨. (EventHandler 인터페이스)
     */

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final ServerSocketChannel serverSocket;
    private final Selector selector; // reactor pattern 의 이벤트 multiplexing 을 위함
    private final EventHandler acceptor;

    @SneakyThrows
    public Reactor(int port) {
        this.selector = Selector.open();
        this.serverSocket = ServerSocketChannel.open();
        this.serverSocket.bind(new InetSocketAddress("localhost", port));
        this.serverSocket.configureBlocking(false);

        this.acceptor = new TcpAcceptEventHandler(selector, serverSocket);
        // accept 이벤트를 등록하며.. acceptor 를 같이 넘긴다.
        this.serverSocket.register(selector, SelectionKey.OP_ACCEPT).attach(this.acceptor);
    }

    @Override
    public void run() {
        // 외부 main 에서 실행됨

        log.info("start server");

        executorService.submit(() -> {
            while (true) {

                selector.select();

                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();

                    dispatch(key);
                }
            }
        });
    }

    private void dispatch(SelectionKey key) {

        // 이벤트 처리 모듈 및 메서드 호출

        EventHandler eventHandler = (EventHandler) key.attachment(); // 이벤트 등록시 같이 넘겨준 오브젝트 받기

        if (key.isAcceptable() || key.isReadable()) {
            eventHandler.handle();
        }
    }
}
