package com.coursera.org.connect.controllers;

import com.coursera.org.connect.managers.JobsManager;
import com.coursera.org.connect.models.Job;
import com.coursera.org.connect.models.request.JobAddRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connect/v1")
public class JobController {
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    private final JobsManager jobsManager;

    public JobController(JobsManager jobsManager) {
        this.jobsManager = jobsManager;
    }

    @PostMapping("/job/addJob")
    public ResponseEntity<String> addJob(@RequestBody JobAddRequest request) {
        try {
            Integer jobId = jobsManager.addJob(request);
            return ResponseEntity.ok("Job added successfully with jobId: " + jobId);
        } catch (Exception e) {
            logger.error("Error adding job", e);
            return ResponseEntity.badRequest().body("Error adding job");
        }
    }

    @GetMapping("/job/recruiter/{recruiterId}")
    public ResponseEntity<List<Job>> getJobsByRecruiterId(@PathVariable Integer recruiterId) {
        try {
            return ResponseEntity.ok(jobsManager.getJobsByRecruiterId(recruiterId));
        } catch (Exception e) {
            logger.error("Error getting jobs by recruiterId", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/job/learner/{userEmailId}")
    public ResponseEntity<List<Job>> getJob(@PathVariable String userEmailId) {
        try {
            return ResponseEntity.ok(jobsManager.getJobsByLearner(userEmailId));
        } catch (Exception e) {
            logger.error("Error getting job", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

}
