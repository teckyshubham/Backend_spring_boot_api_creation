package com.techyShubham.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.techyShubham.security.CustomUserDetailService;
import com.techyShubham.security.JwtAuthenticationEntryPoint;
import com.techyShubham.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	public static final String[] PUBLIC_URLS = {
//			"/api/v1/auth/**",
//			"/v3/api-docs",
//			"/v2/api-docs",
//			"/swagger-resources/**",
//			"/swagger-ui/**",
//			"/webjars/**"
//
//	};

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers("/api/v1/auth/**")
				.permitAll()
				.antMatchers(HttpMethod.GET)
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(this.jwtAuthenticationEntryPoint).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@SuppressWarnings("deprecation")
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
