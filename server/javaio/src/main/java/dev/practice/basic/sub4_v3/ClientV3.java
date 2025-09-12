package dev.practice.basic.sub4_v3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class ClientV3 {

    /**
     * 참고.
     * scanner.nextLine() 에서 대기하는 중 server 가 종료되면 client 와의 연결이 끊긴다.
     * 이때 client 는 대부분의 경우에 scanner.nextLine() 에서 blocking 중일 것이다.
     * scanner.nextLine() 은 client 의 소켓관련 자원이 아니므로 소켓의 자원 정리 트리거와 상관없이 사용자가 문자열을 입력할때까지 무기한 대기한다.
     * -> 이때 server 와의 연결을 종료하는 트리거에 의해 scanner.nextLine blocking 을 벗어나고 싶다면.. sub7_practice.chat.client.WriteHAndler 참고
     *
     * 위 상황에서 console 에 문자를 입력후 엔터 치면,
     * client 에는 RST 패킷이 도착해 있을 것이고 RST 패킷이 왔는데 write 하면 SocketException 발생한다. (이제서야 종료될 것이다.)
     * sub5_exception.connect_close 참고
     */

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
