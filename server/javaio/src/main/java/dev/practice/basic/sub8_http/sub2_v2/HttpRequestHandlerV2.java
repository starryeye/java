package dev.practice.basic.sub8_http.sub2_v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.concurrent.Callable;

import static dev.practice.basic.util.MyThreadLog.threadLog;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpRequestHandlerV2 implements Callable<Void> {

    private final Socket socket;

    public HttpRequestHandlerV2(Socket socket) {
        this.socket = socket;
    }

    /**
     * todo, chrome 으로 한번 요청하면 자꾸 두번 요청이 온것 처럼 로그가 찍힌다.....
     */

    @Override
    public Void call() throws Exception {
        // try with resources
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)) {

            String requestString = requestToString(reader);
            if (requestString.contains("/favicon.ico")) {
                threadLog("favicon request skip..");
                return null;
            }
            threadLog("-------------------------------- HTTP request --------------------------------");
            System.out.print(requestString);
            threadLog("------------------------------------------------------------------------------");

            threadLog("Generating http response...");
            threadLog("-------------------------------- HTTP response -------------------------------");
            if (requestString.startsWith("GET /site1")) {
                site1(writer);
            } else if (requestString.startsWith("GET /site2")) {
                site2(writer);
            } else if (requestString.startsWith("GET /search")) {
                search(writer, requestString);
            } else if (requestString.startsWith("GET / ")) {
                home(writer);
            } else {
                notFound(writer);
            }
            System.out.println();
            threadLog("------------------------------------------------------------------------------");
        }

        return null;
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

    private void site1(PrintWriter writer) {
        String body = "<h1>site1</h1>";
        byte[] bodyBytes = body.getBytes(UTF_8);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=utf-8\r\n");
        sb.append("Content-Length: ").append(bodyBytes.length).append("\r\n");
        sb.append("\r\n"); // header, body 구분 라인
        sb.append(body);

        System.out.print(sb);

        writer.print(sb);
        writer.flush();
    }

    private void site2(PrintWriter writer) {
        String body = "<h1>site2</h1>";
        byte[] bodyBytes = body.getBytes(UTF_8);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=utf-8\r\n");
        sb.append("Content-Length: ").append(bodyBytes.length).append("\r\n");
        sb.append("\r\n"); // header, body 구분 라인
        sb.append(body);

        System.out.print(sb);

        writer.print(sb);
        writer.flush();
    }

    private void search(PrintWriter writer, String requestString) {

        // 첫 라인에 아래와 같이 들어온다..
        // GET /search?q=hello HTTP/1.1
        int startIndex = requestString.indexOf("q=");
        int endIndex = requestString.indexOf(" ", startIndex + 2);
        String query = requestString.substring(startIndex + 2, endIndex);
        String decode = URLDecoder.decode(query, UTF_8);


        StringBuilder body = new StringBuilder();
        body.append("<h1>Search</h1>\n")
                .append("<ul>\n")
                .append("<li>query: ").append(query).append("</li>\n")
                .append("<li>decode: ").append(decode).append("</li>\n")
                .append("</ul>");
        byte[] bodyBytes = body.toString().getBytes(UTF_8);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=utf-8\r\n");
        sb.append("Content-Length: ").append(bodyBytes.length).append("\r\n");
        sb.append("\r\n"); // header, body 구분 라인
        sb.append(body);

        System.out.print(sb);

        writer.print(sb);
        writer.flush();
    }

    private void home(PrintWriter writer) {
        StringBuilder body = new StringBuilder();
        body.append("<h1>home</h1>\n")
                .append("<ul>\n")
                .append("<li><a href='/site1'>site1</a></li>\n")
                .append("<li><a href='/site2'>site2</a></li>\n")
                .append("<li><a href='/search?q=hello'>검색</a></li>\n")
                .append("</ul>");
        byte[] bodyBytes = body.toString().getBytes(UTF_8);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=utf-8\r\n");
        sb.append("Content-Length: ").append(bodyBytes.length).append("\r\n");
        sb.append("\r\n"); // header, body 구분 라인
        sb.append(body);

        System.out.print(sb);

        writer.print(sb);
        writer.flush();
    }

    private void notFound(PrintWriter writer) {
        String body = "<h1>404 페이지를 찾을 수 없습니다...</h1>";
        byte[] bodyBytes = body.getBytes(UTF_8);

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 404 Not Found\r\n");
        sb.append("Content-Type: text/html; charset=utf-8\r\n");
        sb.append("Content-Length: ").append(bodyBytes.length).append("\r\n");
        sb.append("\r\n"); // header, body 구분 라인
        sb.append(body);

        System.out.print(sb);

        writer.print(sb);
        writer.flush();
    }
}
