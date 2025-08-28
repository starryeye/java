package dev.practice.advance.nio.reactor.netty;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageCodec {

    public ByteBuffer encode(final String msg) {

        /**
         * client 로 응답 보낼 데이터를 생성하고 인코딩(UTF-8, ByteBuffer)한다.
         */

        String body = "<html><body><h1>Hello, " + msg + "!</h1></body></html>";
        int contentLength = body.getBytes().length;

        String httpResponse = "HTTP/1.1 200 OK\n" +
                "Content-Type: text/html; charset=utf-8\n" +
                "Content-Length: " + contentLength + "\n\n" + body;

        return StandardCharsets.UTF_8.encode(httpResponse);
    }

    public String decode(final ByteBuffer buffer) {

        /**
         * client 는 http 프로토콜로 문자열을 서버로 전송한다..
         *
         * 서버에서는..
         * Java NIO read 단위인 ByteBuffer 를 받는다.
         *
         * ByteBuffer 는 http 프로토콜로 이루어진 byte 배열이 존재한다.
         * 해당 byte 배열을 디코딩하여..
         * HTTP 의 쿼리 파라미터를 반환 (기본 값은 world)
         */

        buffer.flip();
        String httpRequest = StandardCharsets.UTF_8.decode(buffer).toString().trim();
        String firstLine = httpRequest.split("\n")[0];
        String path = firstLine.split(" ")[1];
        URI uri = URI.create(path);

        String query = uri.getQuery() == null ? "" : uri.getQuery();

        // get name from uri by query string
        Map<String, String> queryMap = Arrays.stream(query.split("&"))
                .map(s -> s.split("="))
                .filter(s -> s.length == 2)
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        return queryMap.getOrDefault("name", "world");
    }
}
