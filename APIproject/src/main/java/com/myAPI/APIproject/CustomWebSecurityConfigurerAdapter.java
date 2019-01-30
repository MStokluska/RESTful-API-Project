package com.myAPI.APIproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.myAPI.APIproject.service.SecurityService;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	final private SecurityService secServiceIn;
	final private PasswordEncoder encoderIn;
	@Autowired
	public CustomWebSecurityConfigurerAdapter(SecurityService secServiceIn, PasswordEncoder encoderIn) {
		super();
		this.secServiceIn = secServiceIn;
		this.encoderIn = encoderIn;
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(secServiceIn).passwordEncoder(encoderIn);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http	.csrf().disable()
				.authorizeRequests()
				.regexMatchers("/admin/.*").hasAnyAuthority("admin")
				.regexMatchers("/supervisor/.*").hasAuthority("supervisor")
				.regexMatchers("/employee/.*").hasAuthority("employee")
				.and()
				.httpBasic();
	}
}
