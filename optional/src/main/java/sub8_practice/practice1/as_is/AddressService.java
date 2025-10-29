package sub8_practice.practice1.as_is;

import sub8_practice.practice1.model.Address;

public class AddressService {

    public static String getCity(Address address) {
        if (address == null) {
            return null;
        }
        return address.getCity();
    }
}
