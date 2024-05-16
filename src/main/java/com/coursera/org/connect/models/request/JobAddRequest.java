package com.coursera.org.connect.models.request;

import com.coursera.org.connect.models.SkillScore;
import lombok.Data;

import java.util.List;

@Data
public class JobAddRequest {
    String jobName;
    String jobDescription;
    Integer recruiterId;
    List<SkillScore> skillScoreList;
}
