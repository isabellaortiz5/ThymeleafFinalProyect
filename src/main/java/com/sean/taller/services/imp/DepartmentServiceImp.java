package com.sean.taller.services.imp;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sean.taller.dao.intfcs.DepartmentDao;
import com.sean.taller.model.hr.Department;
import com.sean.taller.services.intfcs.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImp implements DepartmentService {

	private DepartmentDao dDao;
	
	public DepartmentServiceImp(DepartmentDao dDao) {
		this.dDao = dDao;
	}
	
	
	@Override
	public Department save(Department d) {
		dDao.save(d);
		return findById(d.getDepartmentid());
	}

	@Override
	public Department edit(Department d) {
		dDao.update(d);
		return findById(d.getDepartmentid());
	}

	@Override
	public void delete(Integer id) {
		dDao.delete(findById(id));
	}

	@Override
	public Iterable<Department> findAll() {
		return dDao.findAll();
	}

	@Override
	public Department findById(Integer id) {
		return dDao.findById(id);
	}
	
}
