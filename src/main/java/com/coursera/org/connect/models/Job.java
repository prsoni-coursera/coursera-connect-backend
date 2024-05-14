package com.coursera.org.connect.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Job implements Serializable {
    Integer jobId;
    String jobName;
    String jobDescription;
    String jobLocation;
    String jobType;
    Integer recruiterId;
    List<SkillScore> skillScoreList;
}
