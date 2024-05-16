package com.coursera.org.connect.configuration;

import com.coursera.org.connect.managers.JobsManager;
import com.coursera.org.connect.managers.JobsManagerImpl;
import com.coursera.org.connect.models.Job;
import com.coursera.org.connect.models.User;
import com.coursera.org.connect.persistence.JobStore;
import com.coursera.org.connect.persistence.JobStoreImpl;
import com.coursera.org.connect.persistence.UserStore;
import com.coursera.org.connect.persistence.UserStoreImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class ConnectConfiguration {
    @Bean
    public JobsManager jobsManager() {
        return new JobsManagerImpl(getJobStore());
    }

    @Bean
    public JobStore getJobStore() {
        return new JobStoreImpl(getUsers());
    }

    @Bean
    public UserStore getUserStore() {
        return new UserStoreImpl(getUsers());
    }

    @Bean("userbase")
    public List<User> getUsers() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("users.json");
            List<User> users = mapper.readValue(inputStream, new TypeReference<List<User>>() {});
            System.out.println(users.get(0).toString());
            return users;
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

//    @Bean("userJobs")
//    public Map<String, List<Job>> getUserJobs() {
//        Map<String, List<Job>> userJobs = Collections.emptyMap();
//        try {
//            List<User> users = getUsers();
//            for(User user : users) {
//                userJobs.put(user.getEmail(), new ArrayList<>());
//            }
//            System.out.println(userJobs.size());
//            return userJobs;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return userJobs;
//        }
//    }
}
