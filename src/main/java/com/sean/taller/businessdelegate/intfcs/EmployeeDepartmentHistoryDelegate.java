package com.sean.taller.businessdelegate.intfcs;

import java.util.List;

import com.sean.taller.model.hr.Employeedepartmenthistory;

public interface EmployeeDepartmentHistoryDelegate {
	public Employeedepartmenthistory save(Employeedepartmenthistory e);
	public void update(Employeedepartmenthistory e);
	public void delete(Integer id);
	public Employeedepartmenthistory findById(Integer id);
	public List<Employeedepartmenthistory> findAll();
}
