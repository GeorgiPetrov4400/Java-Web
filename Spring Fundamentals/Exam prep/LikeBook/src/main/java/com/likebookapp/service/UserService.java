package com.likebookapp.service;

import com.likebookapp.model.entity.User;
import com.likebookapp.model.service.UserServiceModel;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);
}
