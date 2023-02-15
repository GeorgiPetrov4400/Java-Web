package bg.softuni.coffeeshop.service;

import bg.softuni.coffeeshop.model.entity.User;
import bg.softuni.coffeeshop.model.service.UserServiceModel;
import bg.softuni.coffeeshop.model.view.UserViewModel;
import bg.softuni.coffeeshop.security.CurrentUser;

import java.util.List;

public interface UserService {

    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

//    void logout();

    User findById(Long id);

    List<UserViewModel> findAllUserAndCountOfOrdersOrderByCountDesc();
}
