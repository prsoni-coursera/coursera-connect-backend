package com.coursera.org.connect.controllers;

import com.coursera.org.connect.models.User;
import com.coursera.org.connect.models.UserType;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@RestController
@RequestMapping("/connect/v1")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // include manager

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser() {
        logger.info("Getting user");
        final User user = new User(1, "John Doe", "johnDoe@gmail.com", "password", UserType.LEARNER, "2021-09-01", "2021-09-01");
        return ResponseEntity.ok(user);
    }
}
