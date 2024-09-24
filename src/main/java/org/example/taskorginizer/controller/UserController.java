package org.example.taskorginizer.controller;

import org.example.taskorginizer.entity.User;
import org.example.taskorginizer.service.JwtService;
import org.example.taskorginizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//defines controller for login and registration of user
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        return jwtService.generateToken(user.getUserName());
    }
}
