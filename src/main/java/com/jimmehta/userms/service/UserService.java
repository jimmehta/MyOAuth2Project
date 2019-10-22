package com.jimmehta.userms.service;

import com.jimmehta.userms.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getSingleUser(Long userId);
    ResponseEntity<Object> createUser(User user);
    ResponseEntity<Object> updateUser(User user);
    ResponseEntity<Object> deleteUser(Long userId);
}
