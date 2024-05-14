package com.coursera.org.connect.configuration;

import com.coursera.org.connect.managers.JobsManager;
import com.coursera.org.connect.managers.JobsManagerImpl;
import com.coursera.org.connect.persistence.JobStore;
import com.coursera.org.connect.persistence.JobStoreImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectConfiguration {
    @Bean
    public JobsManager jobsManager() {
        return new JobsManagerImpl(getJobStore());
    }

    @Bean
    public JobStore getJobStore() {
        return new JobStoreImpl();
    }
}
