package dev.practice.basic.sub8_http.sub1_v1;

import java.io.IOException;

public class HttpServerMainV1 {

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpServerV1 server = new HttpServerV1(SERVER_PORT);
        server.start();
    }
}
