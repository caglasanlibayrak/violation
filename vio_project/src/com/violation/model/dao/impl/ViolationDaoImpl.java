package com.violation.model.dao.impl;

import org.springframework.stereotype.Repository;

import com.violation.model.dao.ViolationDao;
import com.violation.model.entity.Users;
import com.violation.model.entity.Violations;

import java.util.List;

import org.hibernate.Query;

@Repository
public class ViolationDaoImpl extends BaseDao implements ViolationDao {

	@Override
	public Violations getViolationsById(int id) {
		String query = "from Violations where id=:id";
		Query createQuery = getSession().createQuery(query);
		createQuery.setInteger("id", id);
		Violations p = (Violations) createQuery.uniqueResult();
		return p;
	}

	@Override
	public List<Violations> getViolationList(int offset, int limit) {
		String queryString = "from Violations";
		//String queryString = "from Violations where status=:status";
		Query query = getSession().createQuery(queryString);
		//query.setInteger("status", STATUS_ACTIVE);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<Violations> list = query.list();
		return list;
	}

	@Override
	public Violations saveOrUpdateViolations(Violations violation) {

		if (violation.getLatitude() == null) {
			violation.setLatitude(39.1667);
		}
		if (violation.getLongitude() == null) {
			violation.setLongitude(35.6667);
		}
		getSession().saveOrUpdate(violation);
		return violation;
	}

	@Override
	public boolean deleteViolations(int id) {
		Violations violation = getViolationsById(id);
		getSession().delete(violation);
		return true;
	}

	@Override
	public List<Violations> getUserOpenViolationList(Users user) {
		String queryString = "from Violations where userId=:userId and status=:status";
		Query query = getSession().createQuery(queryString);
		query.setInteger("status", STATUS_ACTIVE);
		query.setInteger("userId", user.getId());
		List<Violations> list = query.list();
		return list;
	}

	@Override
	public List<Violations> getUserClosedViolationList(Users user) {
		String queryString = "from Violations where userId=:userId and status=:status";
		Query query = getSession().createQuery(queryString);
		query.setInteger("status", STATUS_DELETED);
		query.setInteger("userId", user.getId());
		List<Violations> list = query.list();
		return list;
	}
}
