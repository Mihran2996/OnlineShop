package onlineshop.demo.service;


import onlineshop.demo.model.Address;

import java.util.List;

public interface AddressService {
     List<Address> addAddress(List<Address> addresses, Address address);
}
