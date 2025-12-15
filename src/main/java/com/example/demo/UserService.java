package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User saveUser(User user) {
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        return repo.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return repo.save(user);
        }).orElseGet(() -> {
            updatedUser.setId(id);
            return repo.save(updatedUser);
        });
    }
}
