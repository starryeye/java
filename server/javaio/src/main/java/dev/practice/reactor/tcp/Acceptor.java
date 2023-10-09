package dev.practice.reactor.tcp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

@Slf4j
@RequiredArgsConstructor
public class Acceptor implements EventHandler {

    /**
     * accept 이벤트가 들어오면 처리한다.
     */

    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    @SneakyThrows
    @Override
    public void handle() {
        // accept 이벤트를 처리할 때 호출되는 메서드 이므로 accept 반환값이 null 이 아님을 보장
        SocketChannel clientSocket = serverSocketChannel.accept();

        new TcpEventHandler(selector, clientSocket); // 매번 만들지 않고 한번 만들어놓고 재활용하면 좀더 성능적으로 개선이 됨.
    }
}
