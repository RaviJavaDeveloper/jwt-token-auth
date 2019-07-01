package org.ravi.config;

import java.util.Collections;
import org.ravi.security.jwt.JwtAuthenticationEntryPoint;
import org.ravi.security.jwt.JwtAuthenticationProvider;
import org.ravi.security.jwt.JwtAuthenticationSuccessHandler;
import org.ravi.security.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class JwtTokenSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;

	@Autowired
	private JwtAuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//disable csrf
		http.csrf().disable();
		//url security mapping
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated()
		.anyRequest().permitAll()
		.and()
		.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//Add authentication filter
		
		http.addFilterBefore(getJwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		
		//Enable cache control
		
	}
	
	
	@Bean
	public JwtAuthenticationTokenFilter getJwtAuthFilter() {
		JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
		jwtAuthenticationTokenFilter.setAuthenticationManager(getAuthManager());
		jwtAuthenticationTokenFilter.setAuthenticationSuccessHandler(successHandler);
		return jwtAuthenticationTokenFilter;
	}

	public AuthenticationManager getAuthManager() {
		
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}
	
}
