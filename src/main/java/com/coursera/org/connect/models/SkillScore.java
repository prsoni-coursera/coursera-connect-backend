package com.coursera.org.connect.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillScore implements Serializable {
    String skillId;
    SkillLevel skillLevel;
}
