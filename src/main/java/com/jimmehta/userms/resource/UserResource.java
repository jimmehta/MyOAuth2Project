package com.jimmehta.userms.resource;

import com.jimmehta.userms.model.User;
import com.jimmehta.userms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        LOGGER.info("******* getting all the users *******");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{userId}")
        public ResponseEntity getSingleUser(@PathVariable Long userId) {
        LOGGER.info("******* getting Single user *******");
        final User user = userService.getSingleUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        LOGGER.info("******* creating Single user *******");
        ResponseEntity userSaved = userService.createUser(user);
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        //    .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.ok(userSaved);
    }

    @PutMapping("/users")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        Long id = user.getId();
        ResponseEntity userFetched = getSingleUser(id);
        if (null == userFetched)
            return ResponseEntity.notFound().build();

        LOGGER.info("******* updating Single user *******");
        user.setId(id);
        ResponseEntity<Object> userUpdated = userService.updateUser(user);
        return ResponseEntity.ok(userUpdated);
    }
    @DeleteMapping("/users/{userId}")
    public void deleteSingleUser(@PathVariable Long userId) {
        LOGGER.info("******* deleting Single user *******");
        userService.deleteUser(userId);
   }
}
