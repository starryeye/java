package annotation.sub5_practice.validator.static_way.validator;

import annotation.sub5_practice.validator.static_way.Team;
import annotation.sub5_practice.validator.static_way.User;

public abstract class Validator {

    private Validator() {}

    public static void validateUser(User user) {

        if (user == null) {
            throw new RuntimeException("user is null");
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new RuntimeException("user name is empty");
        }

        if (user.getAge() < 1 || user.getAge() > 100) {
            throw new RuntimeException("user age is out of range, 1 <= age <= 100");
        }
    }

    public static void validateTeam(Team team) {

        if (team == null) {
            throw new RuntimeException("team is null");
        }

        if (team.getName() == null || team.getName().isEmpty()) {
            throw new RuntimeException("team name is empty");
        }

        if (team.getMemberCount() < 1 || team.getMemberCount() > 999) {
            throw new RuntimeException("team member count is out of range, 1 <= memberCount <= 999");
        }
    }
}
