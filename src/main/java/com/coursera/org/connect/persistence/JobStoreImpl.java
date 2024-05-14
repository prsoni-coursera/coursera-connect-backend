package com.coursera.org.connect.persistence;

import com.coursera.org.connect.models.Job;
import com.coursera.org.connect.models.SkillScore;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class JobStoreImpl implements JobStore {
    
    private static final Logger logger = Logger.getLogger(JobStoreImpl.class.getName());

    Map<Integer, Job> jobMap = new ConcurrentHashMap<>();
    Map<Integer, List<Job>> recruiterJobs = new HashMap<>();
    AtomicInteger jobId = new AtomicInteger(0);

    @Override
    public Integer addJob(String jobName, String jobDescription, Integer recruiterId, List<SkillScore> skillScores) {
        Job newJob = Job.builder()
                .jobId(jobId.incrementAndGet())
                .jobName(jobName)
                .jobDescription(jobDescription)
                .jobLocation("jobLocation")
                .jobType("jobType")
                .recruiterId(recruiterId)
                .skillScoreList(skillScores)
                .build();
        jobMap.put(newJob.getJobId(), newJob);
        recruiterJobs.computeIfAbsent(recruiterId, k -> new ArrayList<>()).add(newJob);
        logger.info(recruiterJobs.get(recruiterId).toString());
        return newJob.getJobId();
    }

    @Override
    public Job getJob(Integer jobId) {
        return jobMap.get(jobId);
    }

    @Override
    public List<Job> getJobsByRecruiter(Integer recruiterId) {
        logger.info("RecruiterId: " + recruiterId);
        return recruiterJobs.get(recruiterId);
    }
}
