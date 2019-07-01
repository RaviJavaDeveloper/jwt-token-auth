package org.ravi.services;

import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Optional;
import org.ravi.models.JwtUser;
import org.ravi.models.User;
import org.ravi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Autowired
	private UserRepository userRepository;
	
	private String secret = "ravi";

	public String generateJwtToken(JwtUser jwtUser) {
		String tokenOrMessage = "User not found";
		Optional<User> optional = userRepository.findByEmail(jwtUser.getEmail());
		if(optional.isPresent()) {
			User user = optional.get();
			if(jwtUser.getPassword().equals(user.getPassword())) {
				Claims claim = Jwts.claims().setSubject(user.getEmail());
				claim.put("password", user.getPassword());
				claim.put("role", user.getRole());
				tokenOrMessage = Jwts.builder().signWith(SignatureAlgorithm.HS256, secret)
						.setClaims(claim)
						.compact();
			} else {
				tokenOrMessage = "username or password is incorrect";
			}
		}
		return tokenOrMessage;
	}

	public String generateCustomToken(JwtUser jwtUser) {
		String tokenOrMessage = "User not found";
		Optional<User> optional = userRepository.findByEmail(jwtUser.getEmail());
		if(optional.isPresent()) {
			User user = optional.get();
			if(jwtUser.getPassword().equals(user.getPassword())) {
				Encoder encoder = Base64.getEncoder();
				String encode = user.getEmail();
				tokenOrMessage = encoder.encodeToString(encode.getBytes());
			} else {
				tokenOrMessage = "username or password is incorrect";
			}
		}
		return tokenOrMessage;
	}
}
