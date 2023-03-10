package domain.web;

import domain.models.dtos.UserRegisterDTO;
import domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public void initUserModel(Model model) {
//        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("userModel", new UserRegisterDTO());
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userModel", bindingResult);

//            bindingResult.rejectValue("email", "UsernameTakenError", "This username is already taken");

            return "redirect:/users/register";
        }

        userService.registerAndLogin(userModel);

        return "redirect:/";
    }
}
