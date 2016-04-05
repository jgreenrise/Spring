package org.test.bookpub;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.CrudRepository;
import org.test.bookpub.dbcount.DbCountRunner;

@Configuration
public class DbCountAutoConfiguration {
	
    @Bean
    public StartupRunner schedulerRunner() {
        return new StartupRunner();
    }
	
	@Bean
	public DbCountRunner dbCountRunner(Collection<CrudRepository> repositories) {
		return new DbCountRunner(repositories);
	}
}
