package com.singfung.demo.controller;

import com.singfung.demo.model.dto.UserDTO;
import com.singfung.demo.model.entity.User;
import com.singfung.demo.model.enumeration.UserStatus;
import com.singfung.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sing-fung
 * @since 1/22/2023
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody @Validated(UserDTO.Insert.class) UserDTO dto) {
        return userService.addUser(dto);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/n_plus_1_solution")
    public List<User> getNPlus1Solution() {
        return userService.listAllUsersWithoutNPlusOne();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody @Validated(UserDTO.Update.class) UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @PutMapping("/{id}/status/{status}")
    public User updateUserStatus(@PathVariable Integer id, @PathVariable UserStatus status) {
        return userService.updateUserStatus(id, status);
    }
}
