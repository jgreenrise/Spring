package com.jsr.ggr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.jsr.ggr.security.MyAuthSuccessHandler;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// @formatter:off
		auth
			.inMemoryAuthentication()
				.withUser("tom").password("123456").roles("USER").and()
				.withUser("bill").password("password").roles("ADMIN").and()
				.withUser("james").password("password").roles("SUPERADMIN");
		// @formatter:off
	}
	
	@Bean
	public MyAuthSuccessHandler myAuthSuccessHandler(){
		return new MyAuthSuccessHandler("/home");
	}
	
	/**
	 * 	
Any URL that starts with "/admin/" will be resticted to users who have the role "ROLE_ADMIN". You will notice that since we are invoking the hasRole method we do not need to specify the "ROLE_" prefix.
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/signup", "/about").permitAll()  
				.antMatchers("/protected/**").hasRole("ADMIN")
				.antMatchers("/confidential/**").hasRole("SUPERADMIN")
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.failureUrl("/login?error=true")
					.successHandler(myAuthSuccessHandler())
					.permitAll()
            .and()
            	.logout()
            		.logoutSuccessUrl("/login")
            		.invalidateHttpSession(true)            
            	.permitAll();
		
		// @formatter:on

		// .formLogin()
		// .permitAll()
		// .defaultSuccessUrl("/home")
		// .loginPage("/login")
		// .loginProcessingUrl("/j_spring_security_check")
		// .failureUrl("/login?error=1")
		// .usernameParameter("username")
		// .passwordParameter("password")
		// .and()
		// .httpBasic()
		// .and()
		// .logout().logoutSuccessUrl("/login?logout")
		// .and()
		// .csrf();

	}

}