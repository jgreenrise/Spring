package org.test.bookpub;

import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;
import org.test.bookpub.dbcount.DbCountRunner;

@SpringBootApplication
@ComponentScan(basePackages = { "org.test.bookpub" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public StartupRunner schedulerRunner() {
		return new StartupRunner();
	}

	@Bean
	public DbCountRunner dbCountRunner(Collection<CrudRepository> repositories) {
		return new DbCountRunner(repositories) {
			@Override
			public void run(String... args) throws Exception {
				logger.info("Manually Declared DbCountRunner");
			}
		};
	}

}
