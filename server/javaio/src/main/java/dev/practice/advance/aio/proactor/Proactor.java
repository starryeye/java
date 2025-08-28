package dev.practice.advance.aio.proactor;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class Proactor implements Runnable{

    /**
     * Java AIO
     * 비동기 non-blocking 방식의 서버
     *
     * OS 가 지원하는 비동기 IO 를 사용한다. (즉, 지원해야 사용가능)
     *
     * 커널이 IO 작업의 완료 여부를 AIO 로 알려주면 반응(처리)하는 서버이다.
     * AIO 는 스레드 풀을 가지고 있고 스레드풀이 등록된 callback 로직을 수행한다.
     *
     * Proactor 패턴
     * Java AIO 를 사용하며..
     *
     * Proactor 는 AIO 에 관심있는(실행할) IO 작업에 callback 을 등록해주고 끝
     *
     * CompletionHandler 는 callback 로직이다.
     *
     * client 는 JavaIOClient, JavaIOMultiClient 를 사용..
     *
     * 0.13 ~ 0.16 초..  걸림..
     *
     */

    private final AsynchronousServerSocketChannel serverSocketChannel;

    @SneakyThrows
    public Proactor(int port) {

        this.serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", port));
    }

    @Override
    public void run() {

        AcceptCompletionHandler acceptCompletionHandler = new AcceptCompletionHandler(serverSocketChannel);
        serverSocketChannel.accept(null, acceptCompletionHandler); //accept 를 호출하고 callback 등록, 템플릿 콜백 패턴
    }
}
