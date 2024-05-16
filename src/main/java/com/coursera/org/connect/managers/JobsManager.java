package com.coursera.org.connect.managers;

import com.coursera.org.connect.models.Job;
import com.coursera.org.connect.models.request.JobAddRequest;

import java.util.List;

public interface JobsManager {
    public Integer addJob(JobAddRequest request);

    public List<Job> getJobsByRecruiterId(Integer recruiterId);

    public List<Job> getJobsByLearner(String userEmailId);
}
