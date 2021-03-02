package onlineshop.demo.service;


import onlineshop.demo.dto.UserDto;
import onlineshop.demo.exception.UserNotFoundException;
import onlineshop.demo.model.Card;
import onlineshop.demo.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User addUser(UserDto userDto, Locale locale, MultipartFile file) throws IOException, MessagingException;

    User getUser(int id) throws UserNotFoundException;

    void delete(int id);

    User findByEmail(String email);

    Optional<User> getByEmail(String email);

    User save(User user);
}
