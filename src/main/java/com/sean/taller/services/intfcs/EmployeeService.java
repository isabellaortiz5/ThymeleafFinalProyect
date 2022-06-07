package com.sean.taller.services.intfcs;

import java.util.Optional;

import com.sean.taller.model.hr.Employee;

public interface EmployeeService {
	public Employee save(Employee vendor);
	public Optional<Employee> findById(Integer id);
	public Iterable<Employee> findAll();	
}
