package org.ravi.resources;

import java.util.List;
import org.ravi.models.JwtUserDetails;
import org.ravi.models.User;
import org.ravi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResources {
	
	@Autowired
	private UserService userService;
	

	@GetMapping("/getAll")
	public List<User> getAllUsers(@AuthenticationPrincipal JwtUserDetails user){
		
		/*
		 * SecurityContext context = SecurityContextHolder.getContext(); Authentication
		 * auth = context.getAuthentication(); JwtUserDetails principal =
		 * (JwtUserDetails)auth.getPrincipal();
		 */

		System.out.println(user);
		return userService.getUsers();
	}
}
