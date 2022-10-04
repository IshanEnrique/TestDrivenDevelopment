package com.tdd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdd.model.User;
import com.tdd.repo.UserRepository;
import com.tdd.service.exception.UserServiceException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public User createUser(String fName, String lName, String email, String password, String repeatPassword) {
		// TODO Auto-generated method stub
		if (null == fName || fName.trim().length() == 0)
			throw new IllegalArgumentException("First name is empty.");
		User user = User.build(fName, lName, email, null);
		try {
			user=userRepo.save(user);
		} catch (RuntimeException e) {
			throw new UserServiceException(e.getMessage());
		}
		return user;
	}

}
