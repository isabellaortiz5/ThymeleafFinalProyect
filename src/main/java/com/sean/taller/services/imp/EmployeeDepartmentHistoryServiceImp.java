package com.sean.taller.services.imp;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sean.taller.dao.intfcs.EmployeeDepartmentHistoryDao;
import com.sean.taller.model.hr.Employeedepartmenthistory;
import com.sean.taller.services.intfcs.EmployeeDepartmentHistoryService;

@Service
@Transactional
public class EmployeeDepartmentHistoryServiceImp implements EmployeeDepartmentHistoryService {

	private EmployeeDepartmentHistoryDao eDao;
	
	public EmployeeDepartmentHistoryServiceImp(EmployeeDepartmentHistoryDao eDao) {
		this.eDao = eDao;
	}
	
	@Override
	public Employeedepartmenthistory save(Employeedepartmenthistory e) {
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
