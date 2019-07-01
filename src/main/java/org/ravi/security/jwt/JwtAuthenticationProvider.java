package org.ravi.security.jwt;

import org.ravi.models.JwtUser;
import org.ravi.models.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	//@Autowired
	//private JwtParser jwtParser;
	
	@Autowired
	private CustomTokenParser customTokenParser;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// Nothing TODO

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		JwtAuhenticationToken jwtAuthToken = (JwtAuhenticationToken) authentication;
		//JwtUser user = jwtParser.parse(jwtAuthToken.getToken());
		JwtUser user = customTokenParser.parse(jwtAuthToken.getToken());
		if(user == null) {
			throw new RuntimeException("Invalid token");
		}
		return new JwtUserDetails(user);
	}

}
