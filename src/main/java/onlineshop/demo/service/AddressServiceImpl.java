package onlineshop.demo.service;

import onlineshop.demo.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {


    @Override
    public List<Address> addAddress(List<Address> addresses, Address address) {
        address.setId(addresses.get(0).getId());
        addresses.set(0, address);
        return addresses;
    }
}
