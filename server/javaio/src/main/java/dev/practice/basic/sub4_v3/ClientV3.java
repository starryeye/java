package dev.practice.basic.sub4_v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class ClientV3 {

    public static void main(String[] args) throws IOException {
        threadLog("client start");

        /**
         * try with resources
         *      socket, input, output 순으로 선언 하였으므로, 자원정리 될때는 output, input, socket 순으로 close 된다.
         *
         * try 내부에서 Exception 이 발생을하던 break 로 while 을 빠져나가서 try 밖으로 나가던..
         * 정상적으로 자원 정리가 이루어진다.
         */
        try(Socket socket = new Socket("localhost", ServerV3.SERVER_PORT);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            threadLog("socket created: " + socket);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("message to send : ");
                String toSend = scanner.nextLine();

                // server 로 메시지 보내기
                output.writeUTF(toSend);                            // scanner.nextLine() 에서 대기하다가 server 종료 이후, console 에 문자를 입력후 엔터 치면 client 에는 RST 패킷이 도착해 있을 것이고 RST 패킷이 왔는데 write 하면 SocketException 발생
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
