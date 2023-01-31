package com.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.ecom.helper.RoleEnum;

@Configuration
@EnableWebSecurity()
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${is-Security-Enabled}")
	private boolean isSecurityEnabled;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails nasserDetails = User.builder()
//										.username("Ness")
//											.password(passwordEncoder.encode("ness"))
//												.roles("CUSTOMER")
//												.build();
//		UserDetails mohitDetails = User.builder()
//										.username("Admin")
//											.password(passwordEncoder.encode("ness"))
//												.roles("ADMIN")
//													.build();
//		
//		
//
//		return new InMemoryUserDetailsManager(nasserDetails, mohitDetails);
//	}
	
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (isSecurityEnabled) {
			http.
			csrf().disable().
			authorizeRequests()
			.antMatchers("/user/**").permitAll()
			.antMatchers("/order/**").hasAuthority(RoleEnum.ADMIN.getRole())
			.antMatchers("/product/**").hasAuthority(RoleEnum.ADMIN.getRole())
			.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement()      																//statless authentication
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			
//			.and()       																			statful authentication
//			.httpBasic()
//			.and()        
//			.formLogin()
//			.loginPage("/signin").permitAll()
//			.loginProcessingUrl("/process-login")
//			.defaultSuccessUrl("/user/")
//			.and()
//			.logout().permitAll();
			
		}
		else {
			http
				.csrf().disable()
				.authorizeRequests()
				.anyRequest()
				.permitAll();
		}
			
	}

	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(myUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		auth.authenticationProvider(authenticationProvider);
	}



	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
