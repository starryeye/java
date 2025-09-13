package dev.practice.basic.sub8_http.sub1_v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;
import static dev.practice.basic.util.MyThreadUtils.mySleep;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpServerV1 {

    private final int port;

    public HttpServerV1(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        threadLog("serverSocket created: " + serverSocket);

        while (true) {
            /**
             * thread 하나로 동작하는 서버, 동시에 1개의 요청만 처리 가능
             */
            Socket socket = serverSocket.accept();
            threadLog("socket accepted: " + socket);
            process(socket);
        }
    }

    private void process(Socket socket) throws IOException {

        /**
         * 참고.
         * PrintWriter 의 autoFlush 옵션을 false 로 했는데..
         * autoFlush=true 는 기본적으로 PrintWriter::println 를 호출하면 자동으로 flush 되게끔하는 옵션이다.
         * 이 옵션은 client 와 연결을 유지하며 라인 단위로 여러번 응답을 보내야할 때 의미가 있는 것이며
         * 현재 코드와 같이 1회성 응답이라면 의미가 없는 옵션이긴하다.
         */

        // try with resources
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)) {

            String requestString = requestToString(reader);
            if (requestString.contains("/favicon.ico")) {
                threadLog("favicon request skip..");
                return;
            }
            threadLog("-------------------------------- HTTP request --------------------------------");
            System.out.print(requestString);
            threadLog("------------------------------------------------------------------------------");

            threadLog("Generating http response...");
            mySleep(5000);

            threadLog("-------------------------------- HTTP response -------------------------------");
            responseToClient(writer);
            System.out.println();
            threadLog("------------------------------------------------------------------------------");
        }
    }

    private static String requestToString(BufferedReader reader) throws IOException {
        /**
         * 빈 줄이 나오면 break 한다.
         * -> header 까지만 읽는다.
         */
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {

            sb.append(line).append("\n");

            if (line.isEmpty()) { // header, body 구분 라인
                break;
            }
        }

        return sb.toString();
    }

    private void responseToClient(PrintWriter writer) {

        String body = "<h1>Hello World</h1>";
        int length = body.getBytes(UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=utf-8\r\n");
        sb.append("Content-Length: ").append(length).append("\r\n");
        sb.append("\r\n"); // header, body 구분 라인
        sb.append(body);

        System.out.print(sb);

        writer.print(sb);
        writer.flush();
    }
}
