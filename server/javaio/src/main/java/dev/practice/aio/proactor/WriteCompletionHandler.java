package dev.practice.aio.proactor;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

@Slf4j
public class WriteCompletionHandler implements CompletionHandler<Integer, Void> {

    /**
     * 커널이 Write 이벤트가 준비 완료 상태를 AIO 로 알리면, AIO(스레드 풀) 에 의해 호출 될 callback 로직이다.
     * CompletionHandler<Integer, Void> 를 구현한다.
     * -> Read, Accept 와 다르게 Write 는 Client 를 기다리지는 않으며 커널의 네트워크 버퍼에 데이터가 채워지면 완료이다.
     *
     * Integer
     * Write 가 완료 되면(커널의 네트워크 버퍼에 데이터가 채워짐) 반환 받는 객체, result 에 해당
     * -> 원래 Write 의 반환은 int 값이다.
     *
     * Void
     * 원래는 attachment 에 해당 되며 WriteCompletionHandler 를 등록할 때 넣어 놓고 callback 이 실행될 때 꺼내서 쓰기 위한 context 에 해당함
     * 여기서는 사용하지 않으므로 void
     */

    private final AsynchronousSocketChannel socketChannel;

    public WriteCompletionHandler(AsynchronousSocketChannel socketChannel) {
        this.socketChannel = socketChannel;

        String response = "I am server";
        ByteBuffer responseByteBuffer = ByteBuffer.wrap(response.getBytes());
        this.socketChannel.write(responseByteBuffer);
    }

    @SneakyThrows
    @Override
    public void completed(Integer result, Void attachment) {
        log.info("write event completed..");

        this.socketChannel.close();
    }

    @SneakyThrows
    @Override
    public void failed(Throwable exc, Void attachment) {
        log.error("write fail", exc);

        this.socketChannel.close();
    }
}
