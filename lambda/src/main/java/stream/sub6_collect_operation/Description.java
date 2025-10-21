package stream.sub6_collect_operation;

public interface Description {

    /**
     * Collection 이나 배열.. 그리고 직접 요소를 전달하는 형태로 Stream 으로 생성할 수 있었다. (sub2 참조)
     * 그러면.. 반대로 Stream 에서 다양한 Collection 이나 배열로 변환할 수 있다면..
     * 배열, Collection 간에 자유로운 변환이 가능할 것이다.
     *      다양한 Collection -----> Stream ------> 다양한 Collection 이므로
     *      다양햔 Collection <------> 다양한 Collection 가능
     *
     * sub6 에서는 collect 연산자를 알아보겠다.
     *      1. Stream 에서 다양한 Collection 으로 변환
     *      2. 다운 스트림 컬렉터
     *
     * 
     * 참고.
     * 배열의 경우... 해당 패키지에서 따로 다루진 않겠다.
     *      배열 -> 스트림
     *          Arrays.stream(배열변수)
     *      스트림 -> 배열
     *          .toArray()
     * -> sub2, sub4.TerminalOperations3, sub5.PrimitiveStream3 에서 예제로 만나볼 수 있다.
     */
}
