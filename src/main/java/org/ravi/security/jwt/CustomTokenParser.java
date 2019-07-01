package org.ravi.security.jwt;

import java.util.Base64;

import org.ravi.models.JwtUser;
import org.ravi.models.User;
import org.ravi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenParser {
	
	@Autowired
	private UserRepository userRepository;
	
	public JwtUser parse(String token) {
		JwtUser juser = null;
		try {
			String email = new String(Base64.getDecoder().decode(token));
			User user = userRepository.findByEmail(email).get();
			juser = new JwtUser();
			juser.setEmail(user.getEmail());
			juser.setPassword(user.getPassword());
			juser.setRole(user.getRole());
		} catch (Exception e) {
			// Nothing TODO
		}
		return juser;

	}
}
