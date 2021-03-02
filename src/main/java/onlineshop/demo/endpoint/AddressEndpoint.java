package onlineshop.demo.endpoint;

import onlineshop.demo.model.Address;
import onlineshop.demo.model.User;
import onlineshop.demo.security.CurrentUser;
import onlineshop.demo.service.AddressServiceImpl;
import onlineshop.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/address")
public class AddressEndpoint {
    @Autowired
    private AddressServiceImpl addressService;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Address address, @AuthenticationPrincipal CurrentUser principal) {
        User user = principal.getUser();
        address.setUser(user);
        user.setAddresses2(addressService.addAddress(user.getAddresses(), address));
        userService.save(user);
        return ResponseEntity.ok(address);
    }
}
