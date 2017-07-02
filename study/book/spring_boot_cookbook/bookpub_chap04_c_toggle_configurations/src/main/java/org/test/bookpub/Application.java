package org.test.bookpub;

import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.test.bookpub.dbcount.DbCountRunner;
import org.test.bookpub.dbcount.EnableDbCounting;

@SpringBootApplication
@EnableScheduling
@EnableDbCounting
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public StartupRunner schedulerRunner() {
		return new StartupRunner();
	}

}
