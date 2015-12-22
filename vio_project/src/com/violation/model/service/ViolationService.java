package com.violation.model.service;

import java.util.List;

import com.violation.model.entity.Users;
import com.violation.model.entity.Violations;

public interface ViolationService {

	public Violations getViolationsById(int id);

	public List<Violations> getViolationList(int offset, int limit);

	public Violations saveOrUpdateViolations(Violations products);

	public boolean deleteViolations(int id);
	
	public List<Violations> getUserOpenViolationList(Users user);
	
	public List<Violations> getUserClosedViolationList(Users user);
}
