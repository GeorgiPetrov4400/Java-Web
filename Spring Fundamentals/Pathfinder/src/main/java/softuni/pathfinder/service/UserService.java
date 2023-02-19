package softuni.pathfinder.service;

import softuni.pathfinder.model.entity.User;
import softuni.pathfinder.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();

    UserServiceModel findById(Long id);

    boolean isNameExist(String username);

    User findCurrentLoginUser();
}
