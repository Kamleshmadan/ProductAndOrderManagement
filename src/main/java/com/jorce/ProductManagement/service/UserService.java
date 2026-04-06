package com.jorce.ProductManagement.service;

import com.jorce.ProductManagement.Repository.RolesRepository;
import com.jorce.ProductManagement.Repository.UserRepository;
import com.jorce.ProductManagement.entity.User;
import com.jorce.ProductManagement.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() ->
                new UserNotFoundException("user not found for userName:" + userName));
    }


    public User insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() ->
                new UserNotFoundException("user not found for userId:" + user.getId()));
        existingUser.setUsername(user.getUsername());
        existingUser.setRole(user.getRole());
        existingUser.setPassword(user.getPassword());
        return userRepository.save(existingUser);
    }

    public User updateUserRole(int userId, String role) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("user not found for userId:" + userId));

        rolesRepository.findByRole(role)
                .orElseThrow(() -> new UserNotFoundException("role not found: " + role));
        user.setRole(role);
        return userRepository.save(user);
    }

    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("user not found for userId:" + userId));

        userRepository.deleteById(userId);
    }


}
