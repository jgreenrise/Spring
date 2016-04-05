package org.test.bookpub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.test.bookpub.dbcount.EnableDbCounting;

@Configuration
@EnableAutoConfiguration
@ComponentScan(excludeFilters=@ComponentScan.Filter(UsedForTesting.class)) 
@EnableScheduling
@EnableDbCounting

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}

@interface UsedForTesting {}
