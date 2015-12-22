package com.violation.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.violation.model.dao.ViolationDao;
import com.violation.model.entity.Users;
import com.violation.model.entity.Violations;
import com.violation.model.service.ViolationService;

@Repository
@Transactional
public class ViolationServiceImpl extends BaseService implements ViolationService {

	@Autowired
	private ViolationDao violationDao;

	public void setViolationDao(ViolationDao violationDao) {
		this.violationDao = violationDao;
	}

	@Override
	public Violations getViolationsById(int id) {
		Violations product = violationDao.getViolationsById(id);
		return product;
	}

	@Override
	public List<Violations> getViolationList(int offset, int limit) {
		return violationDao.getViolationList(offset, limit);
	}

	@Override
	public Violations saveOrUpdateViolations(Violations product) {
		return violationDao.saveOrUpdateViolations(product);
	}

	@Override
	public boolean deleteViolations(int id) {
		return violationDao.deleteViolations(id);
	}

	@Override
	public List<Violations> getUserOpenViolationList(Users user) {
		return violationDao.getUserOpenViolationList(user);
	}

	@Override
	public List<Violations> getUserClosedViolationList(Users user) {
		return violationDao.getUserClosedViolationList(user);
	}

}
