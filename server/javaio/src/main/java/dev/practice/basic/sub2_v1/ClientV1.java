package dev.practice.basic.sub2_v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class ClientV1 {

    public static void main(String[] args) throws IOException {
        threadLog("client start");





        Socket socket = new Socket("localhost", ServerV1.SERVER_PORT);
        threadLog("socket created: " + socket);
        /**
         * 1. localhost:8080 으로 TCP 연결을 시도한다.
         *      localhost 는 IP 주소가 아닌 호스트 이름이므로, InetAddress 를 사용하여 매핑되는 IP 주소를 찾는다.
         *      127.0.0.1:8080 으로 TCP 연결을 시도한다.
         * 2. 3-way handshake 동작을 통해 TCP 연결을 완료한다.
         *      이 때, client 의 port 는 사용가능한 랜덤 port 가 사용된다. (IP 주소는 client 의 IP 주소)
         * 3. 연결이 성공되면 Socket 객체를 반환한다.
         *      연결에 실패하면, ConnectException 예외가 발생한다.
         * 4. Socket 객체를 이용하여, 이 client 역할의 Java 프로세스는 서버와 통신할 수 있게 되었다.
         *
         * 참고.
         * new Socket(host, port) 처럼 host, port 를 인자로 주면, Socket 을 생성할때 TCP 연결을 시도하지만..
         * new Socket() 과 같이 기본 생성자로 Socket 을 생성하면.. TCP 연결은 하지 않는다.
         *      이후, socket.connect(new InetSocketAddress(host, port)) 를 호출해줄 때, TCP 연결을 시도한다.
         *
         */




        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        /**
         * client 와 server 간의 데이터 통신은 Socket 객체가 제공하는 stream 을 이용할 수 있다.
         */





        // send
        String sendMessage = "Hello";
        output.writeUTF(sendMessage);
        threadLog("client -> server: " + sendMessage);
        /**
         * Java String 타입의 값을 DataOutputStream::writeUTF 메서드를 이용하여 편리하게 server 로 전송한다.
         */





        // received
        String receivedMessage = input.readUTF();
        threadLog("client <- server: " + receivedMessage);
        /**
         * server 에서 전송한 byte[] 값을 DataInputStream::readUTF 메서드를 이용하여 편리하게 client 내부 Java String 타입의 값으로 받는다.
         * server 가 보낸 데이터가 client 에 도착 할 때 까지 스레드는 대기한다.
         *      CPU 소모 없이, RUNNABLE 이지만, WAITING 상태 처럼 코드상 blocking 된다.
         */






        // resource close
        input.close();
        output.close();
        socket.close();
        threadLog("resource closed: " + socket);
        /**
         * 사용한 socket, input/output stream 자원을 정리한다.
         */



        threadLog("client end");
    }
}
