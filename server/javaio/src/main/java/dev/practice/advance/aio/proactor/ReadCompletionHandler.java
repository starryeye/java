package dev.practice.advance.aio.proactor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ReadCompletionHandler implements CompletionHandler<Integer, Void> {

    /**
     * 커널이 Read 이벤트의 준비 완료 상태를 AIO 로 알리면, AIO(스레드 풀) 에 의해 호출 될 callback 로직이다.
     * CompletionHandler<Integer, Void> 를 구현한다.
     *
     * Integer
     * Read 가 완료 되면(client 으로 부터 데이터를 받음) 반환 받는 객체, result 에 해당
     * -> 원래 read 의 반환은 int 값이다. EOF 면 -1 을 반환함
     * -> 실제 데이터는 파라미터로 넘긴  ByteBuffer 타입으로 받음
     *
     * Void
     * 원래는 attachment 에 해당 되며 ReadCompletionHandler 를 등록할 때 넣어 놓고 callback 이 실행될 때 꺼내서 쓰기 위한 context 에 해당함
     * 여기서는 사용하지 않으므로 void
     */

    private final ByteBuffer requestByteBuffer;
    private final AsynchronousSocketChannel socketChannel;

    public ReadCompletionHandler(AsynchronousSocketChannel socketChannel) {

        this.requestByteBuffer = ByteBuffer.allocateDirect(1024);
        this.socketChannel = socketChannel;

        this.socketChannel.read(this.requestByteBuffer, null, this); //callback 등록, 템플릿 콜백 패턴
    }

    @SneakyThrows
    @Override
    public void completed(Integer result, Void attachment) {

        log.info("read event completed..");

        this.requestByteBuffer.flip();
        CharBuffer requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer);
        log.info("request={}", requestBody);

        new WriteCompletionHandler(this.socketChannel); // 생성자에서 write 를 호출하며 callback 을 등록한다.
    }

    @Override
    public void failed(Throwable exc, Void attachment) {
        log.error("read fail", exc);
    }
}
