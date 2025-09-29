package dev.practice.advance.nio.reactor.http;

public interface EventHandler {
    /**
     * accept 를 처리하는 별도의 Acceptor(AcceptEventHandler)
     * read 를 처리하는 별도의 Reader(ReadEventHandler)
     */
    void handle();
}
