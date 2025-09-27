package com.example.clothify.controller;

import com.example.clothify.entity.User;
import com.example.clothify.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "Quản lý người dùng")

public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    @Operation(summary = "Danh sách tất cả người dùng", description = "Trả về toàn bộ user trong hệ thống")

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
