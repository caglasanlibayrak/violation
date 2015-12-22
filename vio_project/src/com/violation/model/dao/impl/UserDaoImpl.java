package com.violation.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.violation.model.dao.UserDao;
import com.violation.model.entity.Users;

import java.util.List;

import org.hibernate.Query;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public Users getUsersById(int id) {
		String query = "from Users where id=:id";
		Query createQuery = getSession().createQuery(query);
		createQuery.setInteger("id", id);
		Users b = (Users) createQuery.uniqueResult();
		return b;
	}

	@Override
	public List<Users> getUserList(int offset, int limit) {
		String queryString = "from Users where status=:status";
		Query query = getSession().createQuery(queryString);
		query.setInteger("status", STATUS_ACTIVE);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<Users> list = query.list();
		return list;
	}

	@Override
	public Users getUsersByEmail(String email) {
		String query = "from Users where email=:email";
		Query createQuery = getSession().createQuery(query);
		createQuery.setString("email", email);
		Users b = (Users) createQuery.uniqueResult();
		return b;
	}

	@Override
	public Users getUsersByUserName(String username) {
		String query = "from Users where username=:username";
		Query createQuery = getSession().createQuery(query);
		createQuery.setString("username", username);
		Users b = (Users) createQuery.uniqueResult();
		return b;
	}

	@Override
	public Users saveOrUpdateUser(Users user) {
		user.setStatus(STATUS_ACTIVE);
		getSession().saveOrUpdate(user);
		return user;
	}

	@Override
	public boolean deleteUser(int id) {
		Users user = getUsersById(id);
		getSession().delete(user);
		return true;
	}

	@Override
	public Users getUsersByUsernamePassword(String username, String password) {
		String query = "from Users where username=:username and password=:password";
		Query createQuery = getSession().createQuery(query);
		createQuery.setString("username", username);
		createQuery.setString("password", password);
		Users b = (Users) createQuery.uniqueResult();
		return b;
	}

}
