package sub1_need_for_enum;

public interface Description {

    /**
     * enum 의 필요성에 대해 기술해본다.
     * 예시로는 등급 (SILVER, GOLD, PLATINUM, DIAMOND) 으로 하겠다.
     *
     * enum 을 사용하지 않는 케이스로 알아보는 필요성
     *      1. 변수(String 이나 int 등) 그 자체로 사용할 경우
     *          단점 예시
     *              오타 SIIVER
     *              존재하지 않는 등급 VIP
     *              소문자 gold
     *          이는 컴파일 시 오류 탐지가 불가능하고 런타임때 감지하게 된다.
     *      2. 상수를 사용할 경우
     *          단점 예시
     *              상수도 결국 타입 자체는 String 이나 int 등에서 벗어나지 못하기 때문에 개발자로 상수 사용을 강제하지 못한다.
     *
     */
}
