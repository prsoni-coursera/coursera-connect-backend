package com.coursera.org.connect.persistence;

import com.coursera.org.connect.models.Job;
import com.coursera.org.connect.models.User;

import java.util.List;

public interface UserStore {
    List<Job> getJobsByUserEmailId(String emailId);

    User getUserByEmailId(String emailId) throws Exception;
}
