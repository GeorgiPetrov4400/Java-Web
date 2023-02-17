package bg.softuni.battleships.service;

import bg.softuni.battleships.model.entity.User;
import bg.softuni.battleships.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);
}
