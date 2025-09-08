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
