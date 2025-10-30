package sub8_practice.practice1.to_be;

import sub8_practice.practice1.model.User;

import java.util.Optional;

public class UserService {

    public static Optional<String> getUserCity(User user) {

        return Optional.ofNullable(user)
                .map(User::getAddress)
                .flatMap(AddressService::getCity);
    }
}
