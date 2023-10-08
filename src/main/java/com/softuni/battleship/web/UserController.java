package com.softuni.battleship.web;

import com.softuni.battleship.model.dto.UserLoginDTO;
import com.softuni.battleship.model.dto.UserRegisterDTO;
import com.softuni.battleship.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterModel")
    public UserRegisterDTO initUserRegisterModel() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("userLoginModel")
    public UserLoginDTO initUserLoginModel() {
        return new UserLoginDTO();
    }

    @GetMapping("/register")
    public String register() {

        if (this.userService.isLoggedIn()){
            return "redirect:/users/home";
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (this.userService.isLoggedIn()){
            return "redirect:/users/home";
        }

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterModel", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterModel", bindingResult);
            return "redirect:/users/register";
        }

        this.userService.register(userRegisterDTO);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {

        if (this.userService.isLoggedIn()){
            return "redirect:/users/home";
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (this.userService.isLoggedIn()){
            return "redirect:/users/home";
        }

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginModel", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginModel", bindingResult);
            return "redirect:/users/login";
        }

        if (!this.userService.login(userLoginDTO)){
            redirectAttributes.addFlashAttribute("userLoginModel", userLoginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);
            return "redirect:/users/login";
        }

        return "redirect:/users/home";
    }

    @GetMapping("/logout")
    public String logout(){

        if (!this.userService.isLoggedIn()){
            return "redirect:/";
        }

        this.userService.logout();
        return "redirect:/";
    }
}
