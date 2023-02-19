package softuni.pathfinder.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.pathfinder.model.entity.User;
import softuni.pathfinder.model.enums.Level;
import softuni.pathfinder.model.service.UserServiceModel;
import softuni.pathfinder.repository.UserRepository;
import softuni.pathfinder.service.UserService;
import softuni.pathfinder.util.CurrentUser;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setLevel(Level.BEGINNER);

        userRepository.save(user);
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public void logout() {
        currentUser.setId(null);
        currentUser.setUsername(null);
    }

    @Override
    public UserServiceModel findById(Long id) {
        return userRepository.findById(id).map(user -> modelMapper.map(user, UserServiceModel.class)).orElse(null);
    }

    @Override
    public boolean isNameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public User findCurrentLoginUser() {
        return userRepository.findById(currentUser.getId()).orElse(null);
    }
}
