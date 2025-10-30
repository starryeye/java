package sub8_practice.practice1.to_be;

import sub8_practice.practice1.model.Address;

import java.util.Optional;

public class AddressService {

    public static Optional<String> getCity(Address address) {

        return Optional.ofNullable(address)
                .map(Address::getCity);
    }
}
