package com.coursera.org.connect.persistence;

import com.coursera.org.connect.models.Job;
import com.coursera.org.connect.models.User;
import com.coursera.org.connect.models.UserType;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStoreImpl implements UserStore {

    private final Map<String, User> learnerBase = new HashMap<>(); // <email, user>
    private final Map<String, User> recruiterBase = new HashMap<>(); // <email, user>

    public UserStoreImpl(@Qualifier("userbase") List<User> userBase) {
        for (User user : userBase) {
            if(user.getRole().equals(UserType.LEARNER)) this.learnerBase.put(user.getEmail(), user);
            else if(user.getRole().equals(UserType.RECRUITER)) this.recruiterBase.put(user.getEmail(), user);
        }
    }

    @Override
    public List<Job> getJobsByUserEmailId(String userEmailId) {
        if(learnerBase.containsKey(userEmailId)) return new ArrayList<>();
        else if(recruiterBase.containsKey(userEmailId)) return new ArrayList<>();
        else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User getUserByEmailId(String emailId) throws Exception {
        if(learnerBase.containsKey(emailId)) return learnerBase.get(emailId);
        else if(recruiterBase.containsKey(emailId)) return recruiterBase.get(emailId);
        else {
            throw new RuntimeException("User not found");
        }
    }
}
