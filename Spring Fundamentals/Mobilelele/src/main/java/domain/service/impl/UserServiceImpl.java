package domain.service.impl;

import domain.models.dtos.UserLoginDTO;
import domain.models.dtos.UserRegisterDTO;
import domain.models.entities.User;
import domain.repository.UserRepository;
import domain.service.UserService;
import domain.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private CurrentUser currentUser;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> optionalUser = userRepository.findByUsername(userLoginDTO.getUsername());

        if (optionalUser.isEmpty()) {
            LOGGER.info("User with name [{}] not found", userLoginDTO.getUsername());
            return false;
        }

        var rawPassword = userLoginDTO.getPassword();
        var encodedPassword = optionalUser.get().getPassword();

        boolean success = passwordEncoder.matches(rawPassword, encodedPassword);

        if (success) {
            login(optionalUser.get());
        } else {
            logout();
        }

        return success;
    }

    private void login(User user) {
        currentUser.setLoggedIn(true);
        currentUser.setName(user.getFirstName() + " " + user.getLastName());
    }

    public void logout() {
        currentUser.clear();
    }

    @Override
    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        User newUser = new User();
        newUser.setActive(true);
        newUser.setEmail(userRegisterDTO.getEmail());
        newUser.setFirstName(userRegisterDTO.getFirstName());
        newUser.setLastName(userRegisterDTO.getLastName());
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        newUser = userRepository.save(newUser);

        login(newUser);
    }
}
