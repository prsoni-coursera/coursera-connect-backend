package com.coursera.org.connect.managers;

import com.coursera.org.connect.models.Job;
import com.coursera.org.connect.models.request.JobRequest;
import com.coursera.org.connect.persistence.JobStore;

import java.util.List;

public class JobsManagerImpl implements JobsManager {

    JobStore jobStore;

    public JobsManagerImpl(JobStore jobStore) {
        this.jobStore = jobStore;
    }

    @Override
    public Integer addJob(JobRequest request) {
        return jobStore.addJob(request.getJobName(),
                request.getJobDescription(),
                request.getRecruiterId(),
                request.getSkillScoreList());
    }

    @Override
    public List<Job> getJobsByRecruiterId(Integer recruiterId) {
        return jobStore.getJobsByRecruiter(recruiterId);
    }
}
