package domain.web;

import domain.models.dtos.UserRegisterDTO;
import domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("users/register")
    public String register(UserRegisterDTO userRegisterDTO) {

        userService.registerAndLogin(userRegisterDTO);

        return "redirect:/";
    }
}
