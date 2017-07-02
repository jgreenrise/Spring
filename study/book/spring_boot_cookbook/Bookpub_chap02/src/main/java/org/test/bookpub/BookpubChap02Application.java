package org.test.bookpub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookpubChap02Application {

    public static void main(String[] args) {
        SpringApplication.run(BookpubChap02Application.class, args);
    }
    
    @Bean
    public StartupRunner schedulerRunner() {
        return new StartupRunner();
    }
}
