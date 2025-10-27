package com.curahub.curahub.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Object getAllUsers() {
        return userRepository.findAll();
    }

    public Object getUserByID(long id) {
        return userRepository.getUserById(id);
    }

    public Object getUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(long id, User user) {
        return userRepository.save(user);
    }

}
