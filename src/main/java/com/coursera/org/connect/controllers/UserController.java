package com.coursera.org.connect.controllers;

import com.coursera.org.connect.models.*;
import com.coursera.org.connect.persistence.UserStore;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/connect/v1")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserStore userStore;

    public UserController(UserStore userStore) {
        this.userStore = userStore;
    }


    @GetMapping(value = "/user/{emailId}")
    public ResponseEntity<User> getUser(@PathVariable String emailId) throws Exception {
        logger.info("Getting user");
        return ResponseEntity.ok(userStore.getUserByEmailId(emailId));
    }

    @GetMapping(value = "/user/{emailId}/jobs")
    public ResponseEntity<List<Job>> getJobsByUser(@PathVariable String emailId) {
        logger.info("Getting jobs by user");
        return ResponseEntity.ok(userStore.getJobsByUserEmailId(emailId));
    }


}
