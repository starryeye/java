package annotation.sub5_practice.validator.dynamic_way;

import annotation.sub5_practice.validator.dynamic_way.validator.Validator;

public class Service {

    /**
     * annotation + reflection 기술을 사용하여 동적으로 객체를 검증하는 방법으로 개발
     */

    public static void main(String[] args) {

        User user = new User("user1", 0);
        Team team = new Team(" ", 0);

        try {
            System.out.println("user valid check..");
            Validator.validate(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("team valid check..");
            Validator.validate(team);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
