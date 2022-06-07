package com.sean.taller.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sean.taller.model.hr.Employee;
import com.sean.taller.repository.EmployeeRepository;
import com.sean.taller.services.intfcs.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService{
	private EmployeeRepository er;

	@Autowired
	public EmployeeServiceImp(EmployeeRepository er) {
		this.er = er;
	}
	
	public Iterable<Employee> findAll() {
		return er.findAll();
	}
	
	@Override
	public Employee save(Employee e) {
		return er.save(e);
	}

	@Override
	public Optional<Employee> findById(Integer id) {
		return er.findById(id);
	}
}
