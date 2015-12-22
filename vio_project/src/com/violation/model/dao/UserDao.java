package com.violation.model.dao;

import java.util.List;

import com.violation.model.entity.Users;

public interface UserDao {
	
	public Users getUsersById(int id);
	
	public Users getUsersByEmail(String email);
	
	public Users getUsersByUserName(String username);
	
	 public List<Users> getUserList(int offset, int limit);
	 
	 public Users saveOrUpdateUser(Users user);
	 
	 public boolean deleteUser(int id);
	 
	 public Users getUsersByUsernamePassword(String username, String password);

}
