package io.sub1_input_stream_and_output_stream.sub2_filter_stream;

public interface FilterStream {

    /**
     * Java 프로세스는 외부에 데이터를 주고 받기 위해 여러 객체를 사용하는데 크게 두가지 분류로 나뉜다.
     * basic stream (기반 스트림, 기본 스트림)
     *      직접 외부와 데이터를 byte 단위로 주고 받기 위해 만들어진 객체
     * filter stream (필터 스트림, 보조 스트림)
     *      basic stream 을 wrapping 하여 성능, 사용 편의성을 향상시켜주는 객체
     *
     *
     * filter stream 의 대표적 객체
     * BufferedInputStream / BufferedOutputStream
     *      InputStream / OutputStream 를 상속
     *      1 byte 단위로 IO 가 일어나지 않고 특정 버퍼 사이즈 단위로 IO 가 이루어지도록 하여 성능을 개선함.
     * InputStreamWriter / OutputStreamReader
     *      Reader / Writer 상속
     *      sub2_reader_and_writer 참고
     *          byte 단위로 다루지 않고 인코딩 전의 문자로 다루도록 편의성을 높힘
     * BufferedWriter / BufferedReader
     *      Reader / Writer 상속
     *      sub2_reader_and_writer 참고
     *          byte 단위로 다루지 않고 인코딩 전의 문자로 다루도록 편의성을 높힘
     *          1 byte 단위로 IO 가 일어나지 않고 특정 버퍼 사이즈 단위로 IO 가 이루어지도록 하여 성능을 개선함.
     */
}
