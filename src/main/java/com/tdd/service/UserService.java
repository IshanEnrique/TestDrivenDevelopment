package com.tdd.service;

import com.tdd.model.User;

public interface UserService {

	public User createUser(String fName, String lName, String email, String password, String repeatPassword);

}
