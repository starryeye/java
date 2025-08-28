package dev.practice.advance.nio.reactor.netty;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// [11]
@Slf4j
public class EventLoop implements Runnable{ // Reactor 는 별도의 스레드에서 동작하므로 Runnable 을 구현

    /**
     * Reactor Pattern 을 적용한 Http 서버 구현
     * - [10] 과 코드가 완벽하게 동일하며.. 클래스 이름만 변경되었다.
     *
     * Reactor -> EventLoop
     * Main -> NettyMain (EventLoop 를 여러개 가질 수 있도록 변경함)
     *
     * 즉, 우리는 Spring Webflux 의 기반이 되는 Netty 의 기본적인 원리를 포함한 Http 서버를 만들어 본 것이다.
     */

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final ServerSocketChannel serverSocket;
    private final Selector selector; // reactor pattern 의 이벤트 multiplexing 을 위함
    private final EventHandler acceptor;

    @SneakyThrows
    public EventLoop(int port) {
        this.selector = Selector.open();
        this.serverSocket = ServerSocketChannel.open();
        this.serverSocket.bind(new InetSocketAddress("localhost", port));
        this.serverSocket.configureBlocking(false);

        this.acceptor = new Acceptor(selector, serverSocket);
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
