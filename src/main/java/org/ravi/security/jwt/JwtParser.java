package org.ravi.security.jwt;

import org.ravi.models.JwtUser;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtParser {

	private String secretKey = "ravi";

	public JwtUser parse(String token) {
		JwtUser user = null;
		try {
			Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			user = new JwtUser();
			user.setEmail(body.getSubject());
			user.setPassword(body.get("password").toString());
			user.setRole(body.get("role").toString());
		} catch (Exception e) {
			// Nothing TODO
		}
		return user;

	}
}
