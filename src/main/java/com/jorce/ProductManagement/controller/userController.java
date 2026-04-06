package com.jorce.ProductManagement.controller;

import com.jorce.ProductManagement.entity.User;
import com.jorce.ProductManagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {

    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.insertUser(user));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/{id}/updateRole")
    public ResponseEntity<User> updateUserRole(@PathVariable int id, @RequestParam String role) {
        return ResponseEntity.ok(userService.updateUserRole(id, role));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted user having Id:" + id);
    }

}
