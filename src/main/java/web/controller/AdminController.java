package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.repository.RoleDaoImpl;
import web.service.UserService;

import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDaoImpl roleDao;

    @GetMapping(value = "/admin/")
    public ModelAndView users() {
        List<User> users = userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user,
                                 @RequestParam(value = "userCheck", required = false) boolean userCheck,
                                 @RequestParam(value = "adminCheck", required = false) boolean adminCheck) {
        ModelAndView modelAndView = new ModelAndView();
        Set<Role> roles = new HashSet<>();
        if (userCheck) {
            roles.add(new Role((long) 2, "ROLE_USER"));
        }
        if (adminCheck) {
            roles.add(new Role((long) 1, "ROLE_ADMIN"));
        }
        modelAndView.setViewName("redirect:/admin/");
        user.setRoles(roles);
        userService.edit(user);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/add")
    public String createUserController(@ModelAttribute("user") User user,
                                       @RequestParam(value = "userCheck", required = false) boolean userCheck,
                                       @RequestParam(value = "adminCheck", required = false) boolean adminCheck) {
        Set<Role> roles = new HashSet<>();
        if (userCheck) {
            roles.add(new Role((long) 2, "ROLE_USER"));
        }
        if (adminCheck) {
            roles.add(new Role((long) 1, "ROLE_ADMIN"));
        }
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin/";
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable ("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");
        userService.delete(id);
        return modelAndView;
    }
}
