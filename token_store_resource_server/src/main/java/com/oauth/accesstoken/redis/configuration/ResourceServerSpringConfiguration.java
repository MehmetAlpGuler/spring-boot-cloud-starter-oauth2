package com.oauth.accesstoken.redis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerSpringConfiguration extends ResourceServerConfigurerAdapter {
	@Value("${security.oauth2.resource.id}")
	private String resourceId;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceId).stateless(true);
	}

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		// @formatter:off
		httpSecurity
			.antMatcher("/api/**").csrf().disable()
			.anonymous().disable()
			.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS)
				.permitAll()
				.antMatchers("/api/userMethod").hasAnyRole("USER")
				.antMatchers("/api/adminMethod").hasRole("ADMIN")
				.antMatchers("/api/**")
				.authenticated();
		// @formatter:on
	}

	@Bean
	@Primary
	public RemoteTokenServices remoteTokenServices(final @Value("${auth.server.url}") String checkTokenUrl,
			final @Value("${auth.server.clientId}") String clientId,
			final @Value("${auth.server.clientsecret}") String clientSecret) {
		final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
		remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUrl);
		remoteTokenServices.setClientId(clientId);
		remoteTokenServices.setClientSecret(clientSecret);
		AccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
		remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
		return remoteTokenServices;
	}
}
