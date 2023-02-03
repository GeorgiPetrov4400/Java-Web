package domain.service;

import domain.models.dtos.UserLoginDTO;
import domain.models.dtos.UserRegisterDTO;

public interface UserService {

    public boolean login(UserLoginDTO userLoginDTO);

    public void logout();

    public void registerAndLogin(UserRegisterDTO userRegisterDTO);
}
