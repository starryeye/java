package dev.practice.advance.nio.reactor.tcp;

public interface EventHandler {
    /**
     * accept 를 처리하는 별도의 Acceptor(TcpAcceptEventHandler)
     * read 를 처리하는 별도의 Reader(TcpReadEventHandler)
     */
    void handle();
}
