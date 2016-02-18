package com.karol.dao;

import com.karol.model.User;

public interface UserDAO {

	public boolean addUser(int indexNumber, String password);
	public User loadByIndex(int indexNumber);
}
