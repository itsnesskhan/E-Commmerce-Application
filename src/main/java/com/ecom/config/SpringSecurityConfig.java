package com.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity(debug = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails nasserDetails = User.builder()
										.username("Ness")
											.password("ness")
												.roles("CUSTOMER")
												.build();
		UserDetails mohitDetails = User.builder()
										.username("Admin")
											.password("ness")
												.roles("ADMIN")
													.build();
		
		

		return new InMemoryUserDetailsManager(nasserDetails, mohitDetails);
	}
	
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeHttpRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
			.and()
			.formLogin()
			.loginPage("/login").permitAll().loginProcessingUrl("/dontknow");
			
	}




	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
