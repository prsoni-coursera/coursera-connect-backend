package com.coursera.org.connect.persistence;

import com.coursera.org.connect.models.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class JobStoreImpl implements JobStore {
    
    private static final Logger logger = Logger.getLogger(JobStoreImpl.class.getName());

    private Map<String, List<Job>> userJobs;
    private final List<User> users;

    Map<Integer, Job> jobMap = new ConcurrentHashMap<>();
    Map<Integer, List<Job>> recruiterJobs = new HashMap<>();
    AtomicInteger jobId = new AtomicInteger(0);

    public JobStoreImpl(@Qualifier List<User> users) {
        this.userJobs = new HashMap<>();
        this.users = users;
        for(User user : users) {
            userJobs.put(user.getEmail(), new ArrayList<>());
        }
    }

    @Override
    public Integer addJob(String jobName, String jobDescription, Integer recruiterId, List<SkillScore> skillScores) {
        Job newJob = Job.builder()
                .jobId(jobId.incrementAndGet())
                .jobName(jobName)
                .jobDescription(jobDescription)
                .recruiterId(recruiterId)
                .skillScoreList(skillScores)
                .build();
        jobMap.put(newJob.getJobId(), newJob);
        recruiterJobs.computeIfAbsent(recruiterId, k -> new ArrayList<>()).add(newJob);
        evaluateUsersForJob(newJob);
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

    @Override
    public List<Job> getJobsByLearner(String userEmailId) {
        return userJobs.get(userEmailId);
    }

    private void evaluateUsersForJob(Job job) {
        for (User user : users) {
            if(!user.getRole().equals(UserType.LEARNER)) continue;
            boolean eligible = true;
            for(SkillScore skillScore : job.getSkillScoreList()) {
                if(!skillFilter(skillScore, user)) {
                    eligible = false;
                    break;
                }
            }
            if(eligible)
                userJobs.get(user.getEmail()).add(job);
        }
    }

    private boolean skillFilter(SkillScore skillScore, User user) {
        if(user.getSkillScores().contains(skillScore)) {
            SkillScore userSkillScore = user.getSkillScores().get(user.getSkillScores().indexOf(skillScore));
            if(skillScore.getSkillLevel().equals(SkillLevel.BEGINNER)) {
                return userSkillScore.getSkillLevel().equals(SkillLevel.BEGINNER) || userSkillScore.getSkillLevel().equals(SkillLevel.INTERMEDIATE) || userSkillScore.getSkillLevel().equals(SkillLevel.ADVANCED);
            } else if (skillScore.getSkillLevel().equals(SkillLevel.INTERMEDIATE)) {
                return userSkillScore.getSkillLevel().equals(SkillLevel.INTERMEDIATE) || userSkillScore.getSkillLevel().equals(SkillLevel.ADVANCED);
            } else if (skillScore.getSkillLevel().equals(SkillLevel.ADVANCED)) {
                return userSkillScore.getSkillLevel().equals(SkillLevel.ADVANCED);
            }
        }
        return false;
    }
}
