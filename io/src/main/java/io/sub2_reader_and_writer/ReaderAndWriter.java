package io.sub2_reader_and_writer;

public interface ReaderAndWriter {

    /**
     * InputStream 과 OutputStream 은 ...
     *      Java 프로세스가 외부에 전달할 byte 단위의 데이터를 직접 다룬다.
     *      그러면.. 개발자는 문자를 저장하기 위해
     *      charset 을 이용해서 직접 문자를 인코딩하여 byte 로 변환해줘야한다.
     *      이러한 불편함 때문에 나온게 Reader 와 Writer 이다.
     *
     * Reader 와 Writer 의 구현 객체는 모두 filter stream(보조 스트림) 이다.
     *      개발자가 인코딩/디코딩하는 코드를 작성할 필요없도록 filter stream 에서 지원해주고 실제 IO 는 basic stream 이 담당한다.
     *      Reader 와 Writer 가 직접 charset 을 이용해서 인코딩/디코딩 해주기 때문에 charset 을 설정해줘야한다. (설정하지 않으면 시스템 default charset 이용함)
     *
     *
     * Reader / Writer..
     *      Java 프로세스에서 다양한 외부 대상과 문자를 편리하게 주고 받을 수 있게 추상화 해놓은 추상 클래스이다.
     *      외부와 byte 단위로 데이터를 주고 받는 InputStream / OutputStream 추상클래스와 상속관계가 아니다.
     *          Reader / Writer 내부에서 InputStream / OutputStream 을 사용하는 filter stream 이다.
     *          따라서, InputStream / OutputStream 가 제공하는 메서드와 이름은 동일하지만 파라미터와 반환값이 다르다.
     *      AutoCloseable 을 구현하기 때문에 try-with-resources 를 사용 가능하다.
     *
     * 대표적 구현체
     * OutputStreamWriter / InputStreamReader
     *      Writer / Reader 상속
     *      다양한 basic stream 과 연결하여 사용할 수 있는 범용 객체
     * FileWriter / FileReader
     *      OutputStreamWriter / InputStreamReader 를 상속함.
     *      StreamReaderAndStreamWriter 코드 참고
     * BufferedWriter / BufferedReader
     *      Writer / Reader 상속
     *      Buffered 코드 참고
     */
}
