package org.ravi.resources;

import org.ravi.models.JwtUser;
import org.ravi.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/jwt/generate")
	public String generateJwtToken(@RequestBody JwtUser user) {
		return tokenService.generateJwtToken(user);
	}
	
	@PostMapping("/custom/generate")
	public String generateCustomToken(@RequestBody JwtUser user) {
		return tokenService.generateCustomToken(user);
	}
}
