package org.ravi.services;

import java.util.ArrayList;
import java.util.List;

import org.ravi.models.User;
import org.ravi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	
	public List<User> getUsers(){
		List<User> users = repo.findAll();
		if(users!=null && users.size()>0) {
			return users;
		} else {
			return new ArrayList<>();
		}
	}

}
