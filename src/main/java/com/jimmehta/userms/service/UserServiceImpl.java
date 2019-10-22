package com.jimmehta.userms.service;

import com.jimmehta.userms.model.User;
import com.jimmehta.userms.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    //@Autowired   //Spring 5 onwards does not need this annotation
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("### getting all users ###");
        return userRepository.findAll();
    }

    @Override
    public User getSingleUser(Long userId) {
        LOGGER.info("### getting single user ###");
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public ResponseEntity<Object> createUser(User user) {
        User userSaved = userRepository.save(user);
        return ResponseEntity.ok(userSaved);
    }

    @Override
    public ResponseEntity<Object> updateUser(User user) {
        User userUpdated = userRepository.save(user);
        return ResponseEntity.ok(userUpdated);
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
