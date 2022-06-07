package com.sean.taller.services.intfcs;

import com.sean.taller.model.hr.Employee;

public interface EmployeeService {
	public Employee save(Employee vendor);
	public Iterable<Employee> findAll();	
}
