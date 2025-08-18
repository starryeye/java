package io.sub1_input_stream_and_output_stream;

public interface InputStreamAndOutputStream {

    /**
     * InputStream, OutputStream..
     *      Java 프로세스에서 다양한 외부 대상과 데이터를 주고 받을 수 있게 추상화 해놓은 추상 클래스이다.
     *      "데이터는 byte 단위를 기본으로 사용한다."
     *          write 의 파라미터와 read 의 반환값이 ASCII charset 코드 숫자 값이라 생각해야함
     *      AutoCloseable 을 구현하기 때문에 try-with-resources 를 사용 가능하다.
     *
     *
     * InputStream
     *      int read() throws IOException
     *      int read(byte[] b) throws IOException
     *      int read(byte[] b, int off, int len) throws IOException
     *      byte[] readAllBytes() throws IOException
     * OutputStream
     *      void write(int b) throws IOException
     *      void write(byte b[]) throws IOException
     *      void write(byte b[], int off, int len) throws IOException
     *
     * 대표 상속 객체 (기반 스트림, basic stream)
     * FileInputStream / FileOutputStream
     *      Java 프로세스와 파일간 데이터를 주고 받을 때 사용
     *
     * ByteArrayInputStream / ByteArrayOutputStream
     *      Java 프로세스와 메모리간 데이터를 주고 받을 때 사용
     *
     * SocketInputStream / SocketOutputStream
     *      Java 프로세스와 외부 네트워크(소켓)간 데이터를 주고 받을 때 사용
     *
     * InputStream / PrintStream
     *      Java 프로세스와 console 간 데이터를 주고 받을 때 사용
     *
     * 보조 스트림(filter stream) 은 sub2_filter_stream 참고
     */
}
