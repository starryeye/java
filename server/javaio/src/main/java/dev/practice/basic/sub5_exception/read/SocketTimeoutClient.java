package dev.practice.basic.sub5_exception.read;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SocketTimeoutClient {

    /**
     * client 는 server 와 TCP 연결 이후, 서버로 부터 메시지를 받으려고 3초간 대기한다.
     * 3초 이후 서버로 부터 데이터가 오지 않으면, SocketTimeoutException 발생
     *
     */

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8080);

        InputStream input = socket.getInputStream();

        try {
            socket.setSoTimeout(3000);      // socket timeout(read timeout) 3초 설정
            int read = input.read();        // 3초 대기이후 SocketTimeoutException 발생, 기본값 : 무기한대기
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }

        socket.close();
    }
}
