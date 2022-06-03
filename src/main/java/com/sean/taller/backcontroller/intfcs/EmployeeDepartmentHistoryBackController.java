package com.sean.taller.backcontroller.intfcs;

import com.sean.taller.model.hr.Employeedepartmenthistory;

public interface EmployeeDepartmentHistoryBackController {
	public Employeedepartmenthistory save(Employeedepartmenthistory e);
	public Employeedepartmenthistory update(Integer id, Employeedepartmenthistory e);
	public void delete(Integer id);
	public Employeedepartmenthistory findById(Integer id);
	public Iterable<Employeedepartmenthistory> findAll();
}
