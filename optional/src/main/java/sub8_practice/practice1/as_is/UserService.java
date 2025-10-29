package sub8_practice.practice1.as_is;

import sub8_practice.practice1.model.User;

public class UserService {

    public static String getUserCity(User user) {
        if (user == null) {
            return null;
        }
        return AddressService.getCity(user.getAddress());
    }
}
