package com.sean.taller.services.imp;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sean.taller.dao.intfcs.EmployeeDepartmentHistoryDao;
import com.sean.taller.model.hr.Employee;
import com.sean.taller.model.hr.Employeedepartmenthistory;
import com.sean.taller.services.intfcs.EmployeeDepartmentHistoryService;
import com.sean.taller.services.intfcs.EmployeeService;

@Service
@Transactional
public class EmployeeDepartmentHistoryServiceImp implements EmployeeDepartmentHistoryService {

	private EmployeeDepartmentHistoryDao eDao;
	private EmployeeService es;
	
	public EmployeeDepartmentHistoryServiceImp(EmployeeDepartmentHistoryDao eDao, EmployeeService es) {
		this.eDao = eDao;
		this.es = es;
	}
	
	@Override
	public Employeedepartmenthistory save(Employeedepartmenthistory e, Integer businessentityid) {
		Optional<Employee> optional = es.findById(businessentityid);
		Employee employee = optional.get();
		
		e.setEmployee(employee);
		
		eDao.save(e);
		return findById(e.getId());
	}

	@Override
	public Employeedepartmenthistory edit(Employeedepartmenthistory e) {
		eDao.update(e);
		return findById(e.getId());
	}

	@Override
	public void delete(Integer id) {
		eDao.delete(findById(id));
	}

	@Override
	public Iterable<Employeedepartmenthistory> findAll() {
		return eDao.findAll();
	}

	@Override
	public Employeedepartmenthistory findById(Integer id) {
		return eDao.findById(id);
	}

}
