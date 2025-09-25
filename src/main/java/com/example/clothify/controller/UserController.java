package com.example.clothify.controller;

import com.example.clothify.entity.User;
import com.example.clothify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}/status")
    public User updateStatus(@PathVariable int id,
                             @RequestParam boolean status) {
        return userService.updateStatus(id, status);
    }
    @PutMapping("/{userId}/roles")
    public User addRoleToUser(@PathVariable Integer userId,
                              @RequestParam String roleName) {
        return userService.addRoleToUser(userId, roleName);
    }
}
