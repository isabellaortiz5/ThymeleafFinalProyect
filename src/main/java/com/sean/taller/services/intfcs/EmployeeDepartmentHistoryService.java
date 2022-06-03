package com.sean.taller.services.intfcs;

import com.sean.taller.model.hr.Employeedepartmenthistory;

public interface EmployeeDepartmentHistoryService {


	public Employeedepartmenthistory save(Employeedepartmenthistory e);
	
	public Employeedepartmenthistory edit(Employeedepartmenthistory e);
	
	public void delete(Integer id);
	
	Iterable<Employeedepartmenthistory> findAll();
	
	public Employeedepartmenthistory findById(Integer id);

}
