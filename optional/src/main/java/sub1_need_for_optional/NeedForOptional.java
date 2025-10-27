package sub1_need_for_optional;

import java.util.HashMap;
import java.util.Map;

public class NeedForOptional {

    /**
     * Optional 이 필요한 이유..
     * 1. NullPointerException(NPE) 문제
     *      Java 에서 reference type 변수 에 null 값이 할당된 경우(null 참조 변수)는 "값이 없음"을 표현하는 것이다.
     *      null 참조 변수에 대해 메서드를 호출하면 NPE 가 발생하여 예기치 못한 종료로 이어진다.
     *      따라서 비즈니스 로직을 작성할 때, null 체크를 항상 해주는 습관이 필요하게 된다.
     * 2. 가독성 저하
     *      null 가능성이 있는 변수를 사용하면 조건문으로 계속 null 여부를 확인해줘야한다.
     *      null 체크 로직이 코드에 들어가면 코드 길이가 길어지고 가독성이 떨어진다.
     * 3. 의도가 드러나지 않음
     *      예를 들어 String findNameById(Long id); 라는 추상 메서드를 사용하는 client 개발자는
     *      return 타입이 String 이라 null 이 포함될 수 있는지 없는지를 생각해야한다. (코드만 보고 null 이 절대 포함되지 않는지 알 수 없음)
     */

    public static void main(String[] args) {

        // 내부에 어떤 값이 들어있는지 보이지 않는 어떤 저장소라고 가정
        NameTable nameTable = new NameTable();


        // findNameById() 로 String 타입 변수를 응답 받음.
        String name = nameTable.findNameById(2L);







        /**
         * client 개발자 입장에서는 findNameById() 의 응답값으로 String 타입 변수를 받았지만..
         * 응답값이 null 일 수 있기 때문에 반드시 null 체크 로직을 수행해야한다.
         * String findNameById(long id) 만을 보고 응답 값에 반드시 null 이 포함 되지 않는다라고 판단 불가능
         */

        if (name == null) { // null check
            name = "default name";
        }

        /**
         * client 개발자는 null 가능성 단 하나 때문에
         * 43 ~ 45 line 에 달하는 비즈니스 로직(name 을 upper case 로 변경)과 상관없는 로직을 작성하게 됨.
         */







        String upperCaseName = name.toUpperCase(); // null 참조에 메서드를 호출하면 NPE 발생

        System.out.println("upperCaseName = " + upperCaseName);
    }

    private static class NameTable {

        private final Map<Long, String> map = new HashMap<>();

        public NameTable() {
            map.put(1L, "Kim");
            map.put(2L, "Lee");
        }

        public String findNameById(long id) {
            return map.get(id);
        }
    }
}
