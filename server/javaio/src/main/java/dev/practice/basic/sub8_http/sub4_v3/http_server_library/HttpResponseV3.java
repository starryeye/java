package dev.practice.basic.sub8_http.sub4_v3.http_server_library;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class HttpResponseV3 {

    private static final int DEFAULT_STATUS_CODE = 200;
    private static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

    private int statusCode;
    private String contentType;
    private String body;

    private final PrintWriter writer;

    public HttpResponseV3(PrintWriter writer) {
        this.writer = writer;
    }

    public void writeBody(String body) {
        this.body = body;
    }

    public void flush() {

        int contentLength = body == null ? 0 : body.getBytes(StandardCharsets.UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ").append(statusCode).append(" ").append(getReasonPhrase()).append("\r\n")
                .append("Content-Type: ").append(contentType != null ? contentType : DEFAULT_CONTENT_TYPE).append("\r\n")
                .append("Content-Length: ").append(contentLength).append("\r\n")
                .append("\r\n"); // 헤더 바디 구분
        sb.append(body);

        threadLog(sb); // 응답 데이터 출력

        writer.print(sb);
        writer.flush();
    }

    private String getReasonPhrase() {
        return switch (statusCode) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 500 -> "Internal Server Error";
            default -> "Unknown Status";
        };
    }
}
