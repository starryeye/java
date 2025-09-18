package dev.practice.basic.sub8_http.sub4_v3.http_server_library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;
import static java.nio.charset.StandardCharsets.UTF_8;

public class HttpRequestHandlerV3 implements Runnable {

    private final Socket socket;
    private final HttpServletMappingV3 servletMapping;

    public HttpRequestHandlerV3(Socket socket, HttpServletMappingV3 servletMapping) {
        this.socket = socket;
        this.servletMapping = servletMapping;
    }

    @Override
    public void run() {
        try {
            process(socket);
        } catch (Exception e) {
            threadLog(e);
            e.printStackTrace();
        }
    }

    private void process(Socket socket) throws IOException {

        // try with resources
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)) {

            // 요청 응답 객체 생성
            HttpRequestV3 request = new HttpRequestV3(reader);
            HttpResponseV3 response = new HttpResponseV3(writer);
            threadLog("[HttpRequestHandlerV3::process] HttpRequestV3, HttpResponseV3 created..");


            // 요청 처리 servlet 매핑
            servletMapping.execute(request, response);
            threadLog("[HttpRequestHandlerV3::process] request executed..");


            // 응답
            response.flush();
            threadLog("[HttpRequestHandlerV3::process] response flush..");
        }
    }
}
