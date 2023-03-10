
package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAOImpl;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String viewUsers(Model model) {
        List<User> userListToView = userService.getAllUserList();
        model.addAttribute("userList", userListToView);
        model.addAttribute("newUser", new User());
        return "index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/";
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PatchMapping("/{id}")
    public String Update(@ModelAttribute("user") @Valid User updUser, BindingResult bindingResult, @PathVariable("id") int id)  {
        if (bindingResult.hasErrors())
            return "redirect:/";
//        userService.update(id, updUser);
        userService.update(updUser);
        return "redirect:/";
    }

//    @PostMapping
//    public String addUser(@RequestParam ("name") String name, @RequestParam ("surname") String surname,
//                        @RequestParam ("email") String email, Model model){
//        User user = new User();
//        user.setName(name);
//        user.setSurname(surname);
//        user.setEmail(email);
//        userService.addUser(user);
//        return "redirect:/";
//    }

}