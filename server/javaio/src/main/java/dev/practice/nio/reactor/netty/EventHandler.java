package dev.practice.nio.reactor.netty;

public interface EventHandler {
    /**
     * accept 를 처리하는 별도의 Acceptor
     * read 를 처리하는 별도의 Reader(여기에서는 TcpEventHandler 에 해당)
     */
    void handle();
}
