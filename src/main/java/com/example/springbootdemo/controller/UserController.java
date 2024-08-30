package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.response.ApiResponse;
import com.example.springbootdemo.response.UserListResponse;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<UserListResponse>> getAllUsers() {
        UserListResponse response = userService.getAllUsers();
        return ResponseEntity.ok(new ApiResponse<>(response, "success", 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(new ApiResponse<>(user, "success", 200)))
                .orElse(ResponseEntity.ok(new ApiResponse<>(null, "not found", 404)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        return ResponseEntity.ok(new ApiResponse<>(user, "success", 200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.getUserById(id)
                .map(existingUser -> {
                    user.setId(id);
                    return ResponseEntity.ok(new ApiResponse<>(userService.updateUser(user), "success", 200));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> {
                    userService.deleteUser(id);
                    return ResponseEntity.ok(new ApiResponse<>(null, "User deleted successfully", 200));
                })
                .orElse(ResponseEntity.ok(new ApiResponse<>(null, "User not found", 404)));
    }
}