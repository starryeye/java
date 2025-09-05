package dev.practice.basic.sub5_exception.connect_close.unnormal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class AbnormalClient {

    /**
     * client 가 server 의 TCP 연결 종료를 무시한다면..
     */

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("localhost", 8080);
        threadLog("socket connected : " + socket);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();



        Thread.sleep(1000); // server 가 socket.close() 호출하여 FIN 패킷을 client 로 보낼 때 까지 대기
        // client 의 OS 는 FIN 에 대한 ACK 패킷을 server 로 전달한다.





        // 아래부터 비정상 로직

        // client -> server: PUSH[1]
        output.write(1); // client 는 server 로 데이터를 전송한다.
        // server 는 client 로 부터 ACK 패킷을 받았고 원래 다음 과정으로 FIN 을 기대했지만..
        // 데이터(PUSH 패킷)가 전달되어서 client 로 RST 패킷을 보낸다.





        // client <- server: RST
        Thread.sleep(1000); // server 가 client 로 RST 패킷을 보낼때까지 대기






        try {
            // java.net.SocketException: Connection reset
            int read = input.read(); // RST 패킷을 받은 상태로 read 를 호출하면 SocketException 이 발생한다.
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }






        try {
            // java.net.SocketException: Broken pipe
            output.write(1); // RST 패킷을 받은 상태로 write 를 호출하면 SocketException 이 발생한다.
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
