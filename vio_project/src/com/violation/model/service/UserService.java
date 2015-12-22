package com.violation.model.service;

import java.util.List;

import com.violation.model.entity.Users;

public interface UserService {

	public Users getUsersById(int id);

	public List<Users> getUserList(int offset, int limit);

	public Users saveOrUpdateUser(Users user);

	public boolean deleteUser(int id);

	public Users getUsersByEmail(String email);

	public Users getUsersByUserName(String username);

	public Users getUsersByUsernamePassword(String username, String password);
}
