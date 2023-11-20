package dev.practice.aio.proactor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

@Slf4j
@RequiredArgsConstructor
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Void> {

    /**
     * 커널이 Accept 이벤트가 준비 완료 상태를 AIO 로 알리면, AIO(스레드 풀) 에 의해 호출 될 callback 로직이다.
     * CompletionHandler<AsynchronousSocketChannel, Void> 를 구현한다.
     *
     * AsynchronousSocketChannel
     * accept 가 완료 되면(client 와 connect 됨) 반환 받는 객체, result 에 해당
     *
     * Void
     * 원래는 attachment 에 해당 되며 AcceptCompletionHandler 를 등록할 때 꺼내 쓰기 위해 넣어 놓는 context 에 해당함
     * 여기서는 사용하지 않으므로 void
     */

    private final AsynchronousServerSocketChannel serverSocketChannel;

    @Override
    public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {

        serverSocketChannel.accept(null, this); // 다른 요청을 즉시 받아 주기 위함임.

//        socketChannel.read()
//        // ReadCompletionHandler 는 상태를 가지므로 Thread-safe 하지 않아 객체를 만들어줘야한다.
//        new ReadCompletionHandler();
    }

    @Override
    public void failed(Throwable exc, Void attachment) {
        log.error("accept fail", exc);
    }
}
