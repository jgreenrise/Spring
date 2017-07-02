package org.test.bookpub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookpubChap03Application {

    public static void main(String[] args) {
        SpringApplication.run(BookpubChap03Application.class, args);
    }
    
    @Bean
    public StartupRunner schedulerRunner() {
        return new StartupRunner();
    }
}
