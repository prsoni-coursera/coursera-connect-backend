package com.coursera.org.connect.managers;

import com.coursera.org.connect.models.Job;
import com.coursera.org.connect.models.request.JobRequest;

import java.util.List;

public interface JobsManager {
    public Integer addJob(JobRequest request);

    public List<Job> getJobsByRecruiterId(Integer recruiterId);
}
