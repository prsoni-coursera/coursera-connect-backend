package com.coursera.org.connect.persistence;

import com.coursera.org.connect.models.Job;
import com.coursera.org.connect.models.SkillScore;
import org.springframework.lang.Nullable;

import java.util.List;

public interface JobStore {
    Integer addJob(String jobName, String jobDescription, Integer recruiterId, List<SkillScore> skillScores);

    Job getJob(Integer jobId);

    List<Job> getJobsByRecruiter(Integer recruiterId);

    List<Job> getJobsByLearner(String userEmailId);
}
