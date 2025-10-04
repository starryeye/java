package lambda.sub6_stream.sub1_v1;

import lambda.sub6_stream.sub1_v1.filter.GenericListFilter;
import lambda.sub6_stream.sub1_v1.map.GenericListMapper;

import java.util.ArrayList;
import java.util.List;

public class ExampleUsage2 {

    /**
     * 동일한 기능을 수행하는 두개의 메서드이지만..
     * 명령형 프로그래밍 방식과 선언형 프로그래밍 방식의 차이를 간단히 보기 위해 메서드 두개를 작성
     */

    public static void main(String[] args) {

        // given
        List<User> users = List.of(
                new User("AAA", 10),
                new User("BBB", 20),
                new User("CCC", 30),
                new User("DDD", 40),
                new User("EEE", 50)
        );



        // 30 살 이상의 유저 이름을 추출

        // 람다 활용 O
        List<String> result1 = lambda(users);
        System.out.println("result1 = " + result1);

        // 람다 활용 X
        List<String> result2 = lambda(users);
        System.out.println("result2 = " + result2);
    }

    // 선언형 프로그래밍 방식 (Declarative Programming)
    private static List<String> lambda(List<User> users) {
        List<User> filtered = GenericListFilter.filter(users, user -> user.getAge() >= 30);
        List<String> mapped = GenericListMapper.map(filtered, user -> user.getName());
        return mapped;
    }

    // 명령형 프로그래밍 방식
    private static List<String> direct(List<User> users) {
        List<String> result = new ArrayList<>();

        for (User user : users) {
            if (user.getAge() >= 30) {
                result.add(user.getName());
            }
        }

        return result;
    }

    private static class User {
        private final String name;
        private final Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
}
