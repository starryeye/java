package sub8_practice.practice1.to_be;

import sub8_practice.practice1.model.Address;
import sub8_practice.practice1.model.User;

import java.util.Optional;

public class Controller {

    public static void main(String[] args) {

        User user1 = null;
        User user2 = new User("user 1", null);
        User user3 = new User("user 2", new Address("korea", "seoul"));

        printUserCity(user1);
        System.out.println("---");
        printUserCity(user2);
        System.out.println("---");
        printUserCity(user3);
    }

    private static void printUserCity(User user) {

        String message = Optional.ofNullable(user)
                .flatMap(UserService::getUserCity)
                .map(city -> "user city is " + city)
                .orElse(user == null ? "user is null" : "user address is null");

        System.out.println(message);
    }
}
