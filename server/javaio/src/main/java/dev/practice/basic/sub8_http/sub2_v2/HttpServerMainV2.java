package dev.practice.basic.sub8_http.sub2_v2;

import java.io.IOException;

public class HttpServerMainV2 {

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpServerV2 server = new HttpServerV2(SERVER_PORT);
        server.start();
    }
}
