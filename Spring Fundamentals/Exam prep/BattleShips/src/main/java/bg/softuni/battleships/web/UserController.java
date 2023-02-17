package bg.softuni.battleships.web;

import bg.softuni.battleships.model.dto.UserLoginDTO;
import bg.softuni.battleships.model.dto.UserRegisterDTO;
import bg.softuni.battleships.model.service.UserServiceModel;
import bg.softuni.battleships.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterDTO userRegisterDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterDTO userRegisterDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO",
                            bindingResult);

            return "redirect:register";
        }

        userService.registerUser(modelMapper.map(userRegisterDTO, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDTO userLoginDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",
                            bindingResult);

            return "redirect:login";
        }

        UserServiceModel userServiceModel = userService
                .findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());

        if (userServiceModel == null) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO)
                    .addFlashAttribute("isFound", false);

            return "redirect:login";
        }

        userService.loginUser(userServiceModel.getId(), userServiceModel.getUsername());

        return "redirect:/home";
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/";
    }
}
