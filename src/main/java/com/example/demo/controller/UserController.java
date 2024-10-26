package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "register";
    }

    @PostMapping
    public String addUser(@RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String pass,
                          HttpSession session) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPass(pass);
        userService.addUser(user);

        session.setAttribute("name", name);
        return "redirect:/comments";

    }



    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String name,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userService.findByNameAndPassword(name, password);

        if (user != null) {
            session.setAttribute("name", user.getName());
            return "redirect:/comments";
        } else {
            return "redirect:/register";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("name");
        return "redirect:/comments";
    }


}
