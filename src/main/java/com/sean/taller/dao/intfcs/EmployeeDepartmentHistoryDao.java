package com.sean.taller.dao.intfcs;

import java.util.List;

import com.sean.taller.model.hr.Employeedepartmenthistory;

public interface EmployeeDepartmentHistoryDao {
	public Employeedepartmenthistory save(Employeedepartmenthistory edh);
	public Employeedepartmenthistory update(Employeedepartmenthistory edh);
	void delete(Employeedepartmenthistory edh);
	public Employeedepartmenthistory findById(Integer edhId);
	public List<Employeedepartmenthistory> findAll();
	
}
