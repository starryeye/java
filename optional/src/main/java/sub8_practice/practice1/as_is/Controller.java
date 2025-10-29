package sub8_practice.practice1.as_is;

import sub8_practice.practice1.model.Address;
import sub8_practice.practice1.model.User;

public class Controller {

    public static void main(String[] args) {

        User user1 = new User("user 1", null);
        User user2 = new User("user 2", new Address("korea", "seoul"));

        printUserCity(user1);
        printUserCity(user2);
    }

    private static void printUserCity(User user) {

        String userCity = UserService.getUserCity(user);
        if (userCity != null) {
            System.out.println("userCity is " + userCity);
        } else {
            System.out.println("userCity is null");
        }
    }
}
