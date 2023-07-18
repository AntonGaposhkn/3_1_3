package com.example312.Boot.controller;

import com.example312.Boot.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// пользователь
@Controller
public class UserController {

    @GetMapping("/user")
    public String getInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "users/showUser";
    }
}
