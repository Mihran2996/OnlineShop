package onlineshop.demo.service;

import onlineshop.demo.dto.UserDto;
import onlineshop.demo.exception.DuplicateEntityException;
import onlineshop.demo.exception.UserNotFoundException;
import onlineshop.demo.model.Card;
import onlineshop.demo.model.User;
import onlineshop.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Value("${file.upload.dir}")
    private String uploadDir;
    @Autowired
    private EmailService mailService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(UserDto userDto, Locale locale, MultipartFile file) throws IOException, MessagingException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir, name);
        file.transferTo(image);
        userDto.setProfilePic(name);
        if (!userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            userDto.setCard(new Card());
            User user = modelMapper.map(userDto, User.class);
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setActive(false);
            user.setToken(UUID.randomUUID().toString());
            user.setCard(new Card());
            String link = "http://localhost:8080/rest/user/ativate?email=" + user.getUsername() + "&token=" + user.getToken();
            mailService.sendHtmlEmail(user.getUsername(), "Welcome", user, link, "email/UserWelcomeMail.html", locale);
            return userRepository.save(user);

        } else {
            throw new DuplicateEntityException("Username already exists");
        }
    }

    @Override
    public User getUser(int id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByUsername(email).get();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
