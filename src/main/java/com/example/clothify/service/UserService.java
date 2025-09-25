package com.example.clothify.service;

import com.example.clothify.entity.Role;
import com.example.clothify.entity.User;
import com.example.clothify.repository.RoleRepository;
import com.example.clothify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateStatus(int id, boolean status){
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(status);
        return userRepository.save(user);
    }
    public User addRoleToUser(Integer userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(role);
        return userRepository.save(user);
    }

}
