package dev.practice.basic.sub7_practice.chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class WriteHandler implements Runnable {

    private static final String DELIMITER = "|";

    private final DataOutputStream output;
    private final Client client;

    private boolean isClosed = false;

    public WriteHandler(DataOutputStream output, Client client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            String username = inputUsername(scanner);
            output.writeUTF("/join" + DELIMITER + username);

            while (true) {
                String toSend = scanner.nextLine(); // blocking
                if (toSend.isEmpty()) {
                    continue;
                }

                if (toSend.equals("/exit")) {
                    output.writeUTF(toSend);
                    break;
                }

                if (toSend.startsWith("/")) {
                    output.writeUTF(toSend);
                } else {
                    output.writeUTF("/message" + DELIMITER + toSend);
                }
            }
        } catch (IOException | NoSuchElementException e) {
            threadLog(e);
        } finally {
            client.close();
        }
    }

    private static String inputUsername(Scanner scanner) {
        System.out.println("Write your username: ");
        String username;
        do {
            username = scanner.nextLine(); // blocking
        } while (username.isEmpty());
        return username;
    }

    public synchronized void close() {

        /**
         * 대부분의 상황에서 writeHandler 는 scanner.nextLine 에서 blocking 중이고
         * readHandler 는 input.readUTF() 에서 blocking 중일 것이다.
         * 이러한 상황에서 server 가 연결을 끊으면..
         * 1. readHandler 에서 EOFException 이 발생하며 종료 트리거가 수행된다.
         * 2. Client::close() 가 호출되고 writeHandler 의 close 가 호출된다.
         * 3. System.in.close() 가 호출되면..
         * scanner.nextLine 에서 blocking (현재 writeHandler::run() 을 수행중이던 스레드)에서 벗어나서 NoSuchElementException 가 발생한다.
         */
        if (isClosed) {
            return;
        }

        try {
            System.in.close();
        } catch (IOException e) {
            threadLog(e);
        }

        isClosed = true;

        threadLog("writeHandler shutdown");
    }
}
