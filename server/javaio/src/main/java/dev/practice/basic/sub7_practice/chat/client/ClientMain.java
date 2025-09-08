package dev.practice.basic.sub7_practice.chat.client;

import java.io.IOException;

public class ClientMain {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", PORT);
        client.start();
    }
}
