package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "")
    public String getAllUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @GetMapping(value = "/edit/{id}")
    @Transactional
    public String updateUser(@PathVariable(value = "id", required = true) long id, Model model,
                             RedirectAttributes attributes) {
        model.addAttribute("user", userService.getUserById(id));
        return "new";
    }


    @PostMapping()
    @Transactional
    public String saveUser(@ModelAttribute("user") @Valid User user) {
        userService.createOrUpdate(user);
        return "redirect:/users";
    }

    @PostMapping(value = "/{id}")
    @Transactional
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
