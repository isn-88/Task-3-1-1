package kata.academy.springboot.controller;


import kata.academy.springboot.model.Role;
import kata.academy.springboot.model.User;
import kata.academy.springboot.repository.RoleRepository;
import kata.academy.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;


    @GetMapping()
    public String allUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "all-users";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        user.setPassword("");
        model.addAttribute("user", user);
        model.addAttribute("allRoles", userService.getAllRoles());
        return "new-user";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping("user-update/{id}")
    public String editUser(Model model, @PathVariable("id") long id) {
        User user = userService.findById(id);
        user.setPassword("");
        model.addAttribute("user", user);
        model.addAttribute("allRoles", userService.getAllRoles());
        return "update-user";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @GetMapping("user-delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }


}
