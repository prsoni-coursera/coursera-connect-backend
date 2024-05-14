package com.coursera.org.connect.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkillScore implements Serializable {
    String skillId;
    SkillLevel skillLevel;
}
