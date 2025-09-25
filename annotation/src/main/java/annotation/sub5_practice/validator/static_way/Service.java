package annotation.sub5_practice.validator.static_way;

public class Service {

    /**
     * annotation + reflection 기술을 사용하지 않고 정적으로 객체를 검증하는 방법으로 개발
     */

    public static void main(String[] args) {
        User user = new User("user1", 0);
        Team team = new Team("", 0);

        try {
            System.out.println("user valid check..");
            Validator.validateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("team valid check..");
            Validator.validateTeam(team);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
