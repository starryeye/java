package annotation.sub5_practice.validator.dynamic_way;

import annotation.sub5_practice.validator.dynamic_way.validator.NotEmpty;
import annotation.sub5_practice.validator.dynamic_way.validator.Range;

public class User {

    @NotEmpty(message = "name is empty..")
    private String name;

    @Range(min = 1, max = 100, message = "age is out of range. 1 <= age <= 100")
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
