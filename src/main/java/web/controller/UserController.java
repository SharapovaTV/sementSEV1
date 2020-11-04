package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView index(Principal user) {
        String username = user.getName();
        User user1 = userService.getUserByName(username);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", user1);
        return modelAndView;
    }

}

