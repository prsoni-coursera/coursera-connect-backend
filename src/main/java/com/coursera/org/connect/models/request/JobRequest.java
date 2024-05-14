package com.coursera.org.connect.models.request;

import com.coursera.org.connect.models.SkillScore;
import lombok.Data;

import java.util.List;

@Data
public class JobRequest {
    String jobName;
    String jobDescription;
    String jobLocation;
    String jobType;
    Integer recruiterId;
    List<SkillScore> skillScoreList;
}
