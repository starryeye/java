package dev.practice.basic.sub5_exception.shutdown.normal;

import java.io.*;
import java.net.Socket;

import static dev.practice.basic.util.MyThreadLog.threadLog;

public class NormalCloseClient {

    /**
     * 정상 종료 흐름 예시 (client)
     *
     * socket 의 input stream 으로 read blocking 중 상대에서 TCP 연결 종료를 하면,
     * blocking 중인 read 메서드에서 어떤 값이 리턴 되는지 알아본다.
     *
     * 여기서 read 메서드가 1회만 사용되어서.. read 1회에 응답 데이터 1회라고 헷갈릴 수 도 있는데..
     *      아니다.. InputStream::read() 의 경우 1 byte 만 읽은 것이고
     *      BufferedReader::readLine() 의 경우엔 1 line 만 읽은 것이고
     *      DataInputStream::readUTF() 의 경우엔 1개의 String 만 읽은 것이다.
     *      응답 데이터 1개는 수십 라인의 큰 데이터라고 상상해보자.
     * 보통 응답 데이터를 받을 때, while 문으로 EOF 까지 쭉 받는다.
     */

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8080);
        threadLog("socket connected : " + socket);

        InputStream input = socket.getInputStream();
//        readByInputStream(input, socket);
//        readByBufferedReader(input, socket);
        readByDataInputStream(input, socket);

        threadLog("socket closed : " + socket + ", isClosed : " + socket.isClosed());
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {

        // InputStream::read() 에서는 FIN 패킷 도착시, -1 이 리턴된다.
        int read = input.read(); // 응답 데이터를 쭉 받다가 -1 이 리턴된 것이라 가정한다.
        threadLog("read : " + read);
        if (read == -1) {
            input.close();
            socket.close();
        }
    }

    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {

        // BufferedReader::readLine() 에서는 FIN 패킷 도착시, null 이 리턴된다.
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String readString = br.readLine(); // 응답 데이터를 쭉 받다가 null 이 리턴된 것이라 가정한다.
        threadLog("read : " + readString);
        if (readString != null) {
            br.close();
            socket.close();
        }
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {

        // DataInputStream::readUTF() 에서는 FIN 패킷 도착시, EOFException 이 발생한다.
        DataInputStream dis = new DataInputStream(input);
        try {
            dis.readUTF(); // 응답 데이터를 쭉 받다가 EOFException 이 발생된 것이라 가정한다.
        } catch (EOFException e) {
            threadLog(e);
        } finally {
            dis.close();
            socket.close();
        }
    }
}
