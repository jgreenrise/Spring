package org.test.bookpub;

import java.util.Collection;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.CrudRepository;
import org.test.bookpub.dbcount.DbCountRunner;

@Import(Application.class)
@Configuration
public class DbCountAutoConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	public DbCountRunner dbCountRunner(Collection<CrudRepository> repositories) {
		System.out.println("iNSIDE DBCOUNTAUTOCONFIGURATION");
		return new DbCountRunner(repositories);
	}
}
