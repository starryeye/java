package dev.practice.basic.sub3_v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class ClientV2 {

    public static void main(String[] args) throws IOException {
        threadLog("client start");

        /**
         * try with resources
         *      socket, input, output 순으로 선언 하였으므로, 자원정리 될때는 output, input, socket 순으로 close 된다.
         */
        try(Socket socket = new Socket("localhost", ServerV2.SERVER_PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            threadLog("socket created: " + socket);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("message to send : ");
                String toSend = scanner.nextLine();

                // server 로 메시지 보내기
                output.writeUTF(toSend);
                threadLog("client -> server: " + toSend);

                if (toSend.equals("exit")) {
                    break;
                }

                // server 가 보낸 메시지 읽기
                String received = input.readUTF();
                threadLog("client <- server: " + received);
            }
        } catch (IOException e) {
            threadLog(e);
        }

        threadLog("client end");
    }
}
