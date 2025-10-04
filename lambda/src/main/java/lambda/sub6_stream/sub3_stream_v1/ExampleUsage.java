package lambda.sub6_stream.sub3_stream_v1;

import java.util.List;

public class ExampleUsage {

    public static void main(String[] args) {

        // given
        List<User> users = List.of(
                new User("A", 10),
                new User("BB", 20),
                new User("CCC", 30),
                new User("DDDD", 40),
                new User("EEEEE", 50)
        );


        // 30 살 이상의 유저 이름을 추출
        List<String> result = MyStreamV1.of(users)
                .filter(user -> user.getAge() >= 30)
                .map(User::getName)
                .toList();
        System.out.println("result = " + result);


        // 2 글자 이상의 이름을 가지고 30 살 이하인 유저 객체를 toString
        MyStreamV1.of(users)
                .filter(user -> user.getName().length() >= 2)
                .filter(user -> user.getAge() <= 30)
                .forEach(user -> System.out.println(user.toString()));
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

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
