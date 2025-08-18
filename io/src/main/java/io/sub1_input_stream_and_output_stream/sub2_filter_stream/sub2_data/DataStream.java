package io.sub1_input_stream_and_output_stream.sub2_filter_stream.sub2_data;

import java.io.*;

public class DataStream {

    /**
     * DataOutputStream / DataInputStream ..
     *      filter stream 으로 FilterOutputStream / FilterInputStream 을 상속하여
     *          basic stream (여기서는 FileOutputStream / FileInputStream) 을 데코레이트한다.
     *      지금까지의 charset, basic stream 에서
     *      숫자(int, double, boolean 등)를 Java 프로세스 외부에 전달하는 방법에 대해 생각해보면..
     *      숫자는 byte 로 직접 표현이 가능함에도 그대로 전달하는 방법을 알아본적이 없다.
     *          끼워맞춰서 방법을 생각해본다면.. int type 숫자를 String 으로 변환 시키고 String 을 charset 을 이용해 byte 로 인코딩해서 write 해야할 것 같음..
     *      숫자는 byte 로 바로 표현이 되므로 타입의 크기만큼 직접 외부에 전달하는 방법에 해당하는 객체이다.
     *
     * 참고
     * temp/data.dat 파일을 읽어보면 문자는 보이는데 숫자는 다 깨져있음
     * -> IDE 에서 해당 파일을 읽을 때 일괄적으로 UTF-8로 읽었는데 이 코드에서는 문자는 UTF-8 로 썼고..
     *      숫자는 ASCII 코드로 저장하지 않고 숫자 크기 (int 는 4byte 등) 그대로 실제 숫자 값으로 저장했기 때문에 정상 디코딩 되지 않은 것이다.
     */

    public static void main(String[] args) throws IOException {

        /**
         * 읽기할 때는 쓰기할 때의 순서를 반드시 그대로 지켜야 정상적으로 읽어올 수 있다.
         * 문자를 다룰땐 charset 에 따른 일관된 크기로 읽으므로 순서의 개념이 없었음
         */

        // 쓰기
        FileOutputStream fos = new FileOutputStream("temp/data.dat");
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeUTF("회원A"); // UTF-8 charset 을 이용한다.
        dos.writeInt(20); // Java 의 int 는 4byte 인데 4byte 크기 그대로 외부에 쓰도록한다.
        dos.writeDouble(10.5);
        dos.writeBoolean(true);
        dos.close();

        // 읽기
        FileInputStream fis = new FileInputStream("temp/data.dat");
        DataInputStream dis = new DataInputStream(fis);
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt()); // int type (4byte) 크기만큼 읽고 해당 값을 int type 으로 읽는다.
        System.out.println(dis.readDouble());
        System.out.println(dis.readBoolean());
        dis.close();
    }
}
