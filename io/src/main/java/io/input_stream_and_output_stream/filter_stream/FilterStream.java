package io.input_stream_and_output_stream.filter_stream;

public interface FilterStream {

    /**
     * Java 프로세스는 외부에 데이터를 byte 단위로 주고 받기 위해 InputStream / OutputStream 추상 객체를 사용하는데..
     * InputStream / OutputStream 추상 객체를 상속한 객체는 두가지 분류로 나뉜다.
     * basic stream (기반 스트림, 기본 스트림)
     *      직접 외부와 데이터를 주고 받기 위해 만들어진 객체
     * filter stream (필터 스트림, 보조 스트림)
     *      basic stream 을 wrapping 하여 성능, 사용 편의성을 향상시켜주는 객체
     *
     *
     * InputStream / OutputStream 를 상속하는 filter stream 의 대표적 객체
     * BufferedInputStream / BufferedOutputStream
     *
     */
}
