package com.violation.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.violation.model.dao.UserDao;
import com.violation.model.entity.Users;
import com.violation.model.service.UserService;

@Repository
@Transactional
public class UserServiceImpl extends BaseService implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Users getUsersById(int id) {
		Users user = userDao.getUsersById(id);
		return user;
	}

	@Override
	public List<Users> getUserList(int offset, int limit) {
		return userDao.getUserList(offset, limit);
	}

	@Override
	public Users saveOrUpdateUser(Users user) {
		return userDao.saveOrUpdateUser(user);
	}

	@Override
	public boolean deleteUser(int id) {
		return userDao.deleteUser(id);
	}

	@Override
	public Users getUsersByEmail(String email) {
		Users user = userDao.getUsersByEmail(email);
		return user;
	}

	@Override
	public Users getUsersByUserName(String username) {
		return userDao.getUsersByUserName(username);
	}

	@Override
	public Users getUsersByUsernamePassword(String username, String password) {
		return userDao.getUsersByUsernamePassword(username, password);
	}
}
