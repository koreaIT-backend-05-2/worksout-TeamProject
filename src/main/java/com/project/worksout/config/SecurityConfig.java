package com.project.worksout.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.worksout.config.custom.CustomAuthFailureHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.authorizeRequests()
					.antMatchers("/admin/**")
					.access("hasRole('ROLE_ADMIN')")
					
					.antMatchers("/mypage/**", "/cart/**", "/payment/**")
					
					.authenticated()
					
					.anyRequest()
					.permitAll()
					
					.and()
					
					.formLogin()
					.loginPage("/signin")
					.loginProcessingUrl("/signin")
					.failureHandler(new CustomAuthFailureHandler())
					.defaultSuccessUrl("/mypage/modify");
		}
		
		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	
	
}
