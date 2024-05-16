package com.coursera.org.connect.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    long id;
    String name;
    String email;
    UserType role;
    List<SkillScore> skillScores;
}