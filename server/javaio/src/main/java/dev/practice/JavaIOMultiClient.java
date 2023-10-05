package dev.practice;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class JavaIOMultiClient {

    private static ExecutorService executorService = Executors.newFixedThreadPool(50);

    @SneakyThrows
    public static void main(String[] args) {

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                // 1000 번의 요청을 50 개의 스레드로 동작시킨다. (TODO CountDownLatch 를 사용한 것과 뭐가 다르지..?)

                try (Socket socket = new Socket()) {

                    socket.connect(new InetSocketAddress("localhost", 8080));

                    OutputStream out = socket.getOutputStream();
                    String requestBody = "I am Client";
                    out.write(requestBody.getBytes());
                    out.flush();

                    InputStream in = socket.getInputStream();
                    byte[] responseBytes = new byte[1024];
                    in.read(responseBytes);
                    log.info("response={}", new String(responseBytes).trim());
                }catch (Exception e) {}
            }, executorService);

            futures.add(future);
        }

        // futures 의 모든 결과를 기다린다.
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .join();

        executorService.shutdown();

        long end = System.currentTimeMillis();
        log.info("duration={}", (end - start) / 1000.0);
    }
}
