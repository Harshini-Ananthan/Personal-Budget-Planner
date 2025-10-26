package com.example.Personal.Budget.Planner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Personal.Budget.Planner.entity.User;
import com.example.Personal.Budget.Planner.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long user_id) {
        return userRepository.findById(user_id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long user_id, User userDetails) {
        User user = userRepository.findById(user_id).orElseThrow();
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setContact_no(userDetails.getContact_no());
        user.setJoin_date(userDetails.getJoin_date());
        return userRepository.save(user);
    }

    public void deleteUser(Long user_id) {
        userRepository.deleteById(user_id);
    }
}