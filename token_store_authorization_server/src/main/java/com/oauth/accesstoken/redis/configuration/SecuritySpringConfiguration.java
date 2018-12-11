package com.oauth.accesstoken.redis.configuration;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.oauth.accesstoken.redis.entity.RoleEntity;
import com.oauth.accesstoken.redis.entity.UserEntity;
import com.oauth.accesstoken.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecuritySpringConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void postConstruct() {
		if (userService.findOne("user") == null) {
			UserEntity user = new UserEntity("user", passwordEncoder.encode("user"), true, true, true, true,
					Arrays.asList(new RoleEntity("ROLE_USER", "user role name", null)));
			userService.save(user);
		}
		if (userService.findOne("admin") == null) {
			UserEntity admin = new UserEntity("admin", passwordEncoder.encode("admin"), true, true, true, true,
					Arrays.asList(new RoleEntity("ROLE_ADMIN", "admin role name", null)));
			userService.save(admin);
		}
		if (userService.findOne("other") == null) {
			UserEntity admin = new UserEntity("other", passwordEncoder.encode("other"), true, true, true, true,
					Arrays.asList(new RoleEntity("ROLE_OTHER", "other role name", null)));
			userService.save(admin);
		}
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// @formatter:off
		httpSecurity
			.formLogin().disable()
			.anonymous().disable()
			.httpBasic()
			.and()
			.requestMatchers().antMatchers("/oauth/check_token")
			.and()
			.authorizeRequests().anyRequest().denyAll();
		// @formatter:on
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
