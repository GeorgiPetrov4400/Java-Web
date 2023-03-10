package domain.web;

import domain.models.dtos.UserLoginDTO;
import domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    private UserService userService;

    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserLoginDTO initUserModel() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @GetMapping("/logout")
    public String logout() {
        userService.logout();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.userService.login(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userModel", userLoginDTO);

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userModel", bindingResult);

            bindingResult.rejectValue("password", "InvalidPasswordError", "Invalid password.");

            return "redirect:/users/login";
        }

        return "redirect:/";
    }
}
