package com.example312.Boot.controller;

import com.example312.Boot.model.User;
import com.example312.Boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//Сюда заходит только админ
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getInfo(@AuthenticationPrincipal User admin, Model model) {
        model.addAttribute("user", admin);
        return "users/showUser";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/all";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/showUser";
    }


    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") User user, @RequestParam String role) {
        userService.addUser(user, role); // Добавляем этого юзера в БД
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User updateUser, @PathVariable("id") long id, @RequestParam String role) {
        userService.updateUser(id, updateUser, role); //Находим по id того юзера, которого надо изменить
        return "redirect:/admin/users";
    }


    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
