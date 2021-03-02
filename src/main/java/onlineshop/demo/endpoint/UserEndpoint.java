package onlineshop.demo.endpoint;

import onlineshop.demo.dto.AuthenticationRequest;
import onlineshop.demo.dto.AuthenticationResponse;
import onlineshop.demo.dto.UserDto;
import onlineshop.demo.exception.UserNotFoundException;
import onlineshop.demo.model.User;
import onlineshop.demo.service.UserServiceImpl;
import onlineshop.demo.util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/rest/user")
public class UserEndpoint {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody AuthenticationRequest authenticationRequest) throws UserNotFoundException {
        User user = null;
        user = userService.findByEmail(authenticationRequest.getEmail());
        if (passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getUsername());
            UserDto userDto = modelMapper.map(user, UserDto.class);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .token(token)
                    .userDto(userDto)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    //veradardznum enq bolor usernerin
    @GetMapping("/users")
    public List<User> users() {
        return userService.getAllUsers();
    }

    //stexcum enq user
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserDto user, Locale locale, MultipartFile file) throws IOException, MessagingException {
        return ResponseEntity.ok(userService.addUser(user, locale,file));
    }

    @GetMapping("/activate/{email},{token}")
    public ResponseEntity activatedAccount(@PathVariable("email") String email,
                                           @PathVariable("token") String token) {
        Optional<User> userFromDb = userService.getByEmail(email);
        if (userFromDb.isPresent()) {
            User user = userFromDb.get();
            if (user.getToken().equals(token)) {
                user.setActive(true);
                user.setToken("");
                userService.save(user);
            }
        }
        return ResponseEntity.ok(userFromDb.get());
    }

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") int id) throws UserNotFoundException {
        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteUser(@PathVariable("id") int id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
